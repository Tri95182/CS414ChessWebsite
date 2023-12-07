package com.tco.game;

public class Board {
    private final Square[][] board;


    public Board() {
        this.board = new Square[8][8];
        initMiddlerows();
        initFrontrows();
        initBackrows();
    }

    void initMiddlerows() {
        for (int row = 2; row < 6; row++) {
            for (int col = 0; col < 8; col++) {
                this.board[row][col] = new Square(new Position(row, col));
            }
        }
    }

    // Puts the pawns in their respective places.
    void initFrontrows() {
        int row = 1;
        for (int i = 0; i < 8; i++) {
            setPieces(row, i, BoardPiece.PieceType.PAWN);
        }
    }

    // Puts the rest of the pieces in their respective places.
    void initBackrows() {
        int row = 0;
        for (int i = 0; i < 8; i++) {
            BoardPiece.PieceType type = null;
            switch (i) {
                case 0:
                case 7:
                    type = BoardPiece.PieceType.ROOK;
                    break;
                case 1:
                case 6:
                    type = BoardPiece.PieceType.KNIGHT;
                    break;
                case 2:
                case 5:
                    type = BoardPiece.PieceType.BISHOP;
                    break;
                case 3:
                    type = BoardPiece.PieceType.QUEEN;
                    break;
                case 4:
                    type = BoardPiece.PieceType.KING;
                    break;
            }
            setPieces(row, i, type);
        }
    }

    // Given the indices for a white piece, it initializes the array
    // and the piece at both the white and black location.
    void setPieces(int row, int col, BoardPiece.PieceType type) {
        //(1,0) (1, 7)
        //board[row][col] = new BoardPiece(new Position(row, col), type, BoardPiece.Color.WHITE)
        BoardPiece white = makeNullPosPiece(type, BoardPiece.Color.WHITE);
        white.setPosition(new Position(row, col));

        BoardPiece black = makeNullPosPiece(type, BoardPiece.Color.BLACK);
        black.setPosition(white.getOpposite());

        this.board[row][col] = new Square(white.getPosition(), white);
        
        Position blackPos = black.getPosition();
        this.board[blackPos.getRow()][blackPos.getCol()] = new Square(black.getPosition(), black);
    }

    BoardPiece makeNullPosPiece(BoardPiece.PieceType type, BoardPiece.Color color) {
        switch (type) {
            case QUEEN:
                return new Queen(type, color);
            case KING:
                return new King(type, color);
            case KNIGHT:
                return new Knight(type, color);
            case PAWN:
                return new Pawn(type, color);
            case ROOK:
                return new Rook(type, color);
            case BISHOP:
                return new Bishop(type, color);
        }
        return null;
    }

    public Square[][] getState() {
        return this.board;
    }

    public Square getSquare(Position pos) {
        return getSquare(pos.getRow(), pos.getCol());
    }

    public Square getSquare(int row, int col) {
        return this.board[row][col];
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.board[i][j].getPiece() == null) {
                    result.append(" ");
                } else {
                    result.append(this.board[i][j].getPiece().name());
                }
            }
        }
        return result.toString();
    }

}
