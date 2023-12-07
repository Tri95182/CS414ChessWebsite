package com.tco.game;
import java.util.ArrayList;

public class MovePiece {
    
    private Board board;
    private boolean isValidMove;
    private boolean promote;
    private Square castle;
    private Square passant;
    private Square startSquare;
    private Square endSquare;
    private Position startPosition;
    private Position endPosition;
    private BoardPiece boardPiece;
    private BoardPiece capturedPiece;

    MovePiece(Board board, Square startSquare, Square endSquare){
        this.board = board;
        this.startSquare = startSquare;
        this.endSquare = endSquare;
        this.boardPiece = startSquare.getPiece();
        this.startPosition = startSquare.getPosition();
        this.endPosition = endSquare.getPosition();
        this.promote = false;
        this.passant = null;
        this.castle = null;
        this.isValidMove = isValidMove();
    }

    private boolean isValidMove() {
        Position[] moves = this.boardPiece.getPossibleMovePositions(this.boardPiece.getPossibleMoves());
        boolean foundMove = false;
        for (int i = 0; i < moves.length; i++) { // Check move against piece's possible moves
            if (this.endPosition.equals(moves[i])) {
                foundMove = true;
                break;
            }
        }

        if (!foundMove || checkForSameColorPiece(this.endSquare)) { // Check end square
            return false;
        }

        if (!validateMoveByPieceType(this.boardPiece.name())) {
            return false;
        }
        return true;
    }

    private boolean checkForSameColorPiece(Square sq) {
        if (sq.getPiece() == null) return false;
        if (this.boardPiece.getColor() == sq.getPiece().getColor()) return true;
        else return false;
    }

    private boolean validateMoveByPieceType(BoardPiece.PieceType type) {
        switch (type) { // Knights dont require path-checks
            case BISHOP:
            case ROOK:
            case QUEEN:
                if (collisionOnPath()) return false;
                break;
            case PAWN:
                if (checkPawnMove()) return false;
                break;
            case KING:
                if (!checkForValidCastle()) return false;
                break;
        }
        return true;
    }

    private boolean checkForValidCastle() {
        int colDif = this.startPosition.getCol() - this.endPosition.getCol();
        if (Math.abs(colDif) == 2) {
            Square rookSquare = validateCastleMove(colDif);
            if (rookSquare != null) {
                this.castle = rookSquare;
                return true;
            }
            return false;
        }
        return true;
    }

    private Square validateCastleMove(int side) {
        Square rookSquare = null;
        if (side > 0) rookSquare = this.board.getSquare(this.startPosition.getRow(), 0); // Left
        else rookSquare = this.board.getSquare(this.startPosition.getRow(), 7); // Right
        if (rookSquare.getPiece() == null) return null;
        if (rookSquare.getPiece().hasMoved()) return null;
        if (collisionOnPath(this.startPosition, rookSquare.getPosition())) return null;
        return rookSquare;
    }

    private boolean checkPawnMove() {
        boolean validPawnMove = false;
        int passantRow = 4;
        if (this.boardPiece.getColor() == BoardPiece.Color.BLACK) passantRow = 3;
        if (this.startPosition.getCol() != this.endPosition.getCol()) { // Check diagonal space for capturable piece
            if (this.startPosition.getRow() == passantRow) {
                Square passSquare = this.board.getSquare(this.startPosition.getRow(), this.endPosition.getCol());
                if (checkForOtherPawn(passSquare) && this.endSquare.getPiece() == null) {
                    validPawnMove = true;
                    this.passant = passSquare;
                }
            }
            if (this.endSquare.getPiece() != null && !checkForSameColorPiece(this.endSquare) && this.passant == null) validPawnMove = true;
        }
        else if (!collisionOnPath() && this.endSquare.getPiece() == null) validPawnMove = true; // Check forward space(s) for non-capturable pieces
        if (this.endPosition.getRow() == 0 || this.endPosition.getRow() == 7) this.promote = true;
        return validPawnMove;
    }

    private boolean checkForOtherPawn(Square sq) {
        if (sq.getPiece() == null) return false;
        if (this.boardPiece.getColor() != sq.getPiece().getColor() && sq.getPiece().name() == BoardPiece.PieceType.PAWN) return true;
        else return false;
    }

    private boolean collisionOnPath() {
        int row = this.startPosition.getRow();
        int col = this.startPosition.getCol();
        int endRow = this.endPosition.getRow();
        int endCol = this.endPosition.getCol();
        int rowDif = endRow - row;
        int colDif = endCol - col;
        int rowInc = 0;
        int colInc = 0;
        if (rowDif != 0) rowInc = rowDif/Math.abs(rowDif);
        if (colDif != 0) colInc = colDif/Math.abs(colDif);
        row += rowInc;
        col += colInc;
        rowDif = endRow - row;
        colDif = endCol - col;

        while (rowDif != 0 || colDif != 0) {
            if (this.board.getSquare(row, col).getPiece() != null) return true;
            row += rowInc;
            col += colInc;
            rowDif = endRow - row;
            colDif = endCol - col;
        }
        return false;
    }

    private boolean collisionOnPath(Position start, Position end) {
        Position storeEnd = this.endPosition;
        Position storeStart = this.startPosition;
        this.endPosition = end;
        this.startPosition = start;
        boolean collision = collisionOnPath();
        this.endPosition = storeEnd;
        this.startPosition = storeStart;
        return collision;
    }

    public Square getStartSquare() {
        return this.startSquare;
    }

    public Square getEndSquare() {
        return this.endSquare;
    }

    public BoardPiece getPiece() {
        return this.boardPiece;
    }

    public boolean getIsValidMove(){
        return this.isValidMove;
    }
    
    public boolean executeMove(){
        if (getIsValidMove()){
            this.capturedPiece = this.endSquare.getPiece();
            this.boardPiece.setPosition(this.endPosition);
            this.boardPiece.setHasMoved();
            this.endSquare.setPiece(this.boardPiece);
            this.startSquare.clearPiece();
            if (this.promote) promotePawn();
            if (passant != null) executePassant();
            if (castle != null) executeCastle();
            return true;
        }
        return false;
    }

    private void promotePawn() {
        this.endSquare.setPiece(new Queen(BoardPiece.PieceType.QUEEN, this.boardPiece.getColor()));
        this.endSquare.getPiece().setPosition(this.endSquare.getPosition());
    }

    private void executePassant() {
        this.capturedPiece = this.passant.getPiece();
        this.passant.clearPiece();
    }

    private void executeCastle() {
        BoardPiece castleRook = this.castle.getPiece();
        int castleCol = (this.startPosition.getCol() + this.endPosition.getCol()) / 2;
        this.board.getSquare(this.startPosition.getRow(), castleCol).setPiece(castleRook);
        castleRook.setPosition(new Position(this.startPosition.getRow(), castleCol));
        castleRook.setHasMoved();
        this.castle.clearPiece();
    }
}
