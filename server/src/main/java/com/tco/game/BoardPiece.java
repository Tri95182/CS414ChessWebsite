package com.tco.game;
import java.util.ArrayList;

public abstract class BoardPiece {
    private final PieceType pieceType;
    private Color pieceColor;
    private Position position;
    private boolean hasMoved;

    BoardPiece(Position position, PieceType pieceType, Color pieceColor) {
        this.position = position;
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.hasMoved = false;
    }

    BoardPiece(PieceType pieceType, Color pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
        this.hasMoved = false;
    }

    public boolean hasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved() {
        this.hasMoved = true;
    }

    public Color getColor() {
        return this.pieceColor;
    }

    public void setColor(Color pieceColor) {
        this.pieceColor = pieceColor;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PieceType name() {
        return this.pieceType;
    }

    public int compareTo(PieceType o) {
        return pieceType.compareTo(o);
    }

    // Returns the index of it's corresponding piece on the other team at game start.
    public Position getOpposite() {
        int row = this.position.getRow();
        int col = this.position.getCol();
        if (this.pieceColor == Color.BLACK) {
            if (row == 7) return new Position(row - 7, col);
            else return new Position(row - 5, col);
        }
        if (row == 0) return new Position(row + 7, col);
        else return new Position(row + 5, col);
    }

    public Position[] getPossibleMovePositions(ArrayList<int[]> moves) {
        Position[] positions = new Position[moves.size()];
        for (int i = 0; i < moves.size(); i++) {
            int[] move = moves.get(i);
            Position pos = new Position(move[0], move[1]);
            positions[i] = pos;
        }
        return positions;
    }

    public abstract ArrayList<int[]> getPossibleMoves();

    enum PieceType {
        PAWN,
        KNIGHT,
        BISHOP,
        ROOK,
        QUEEN,
        KING
    }

    enum Color {
        BLACK,
        WHITE
    }

}
