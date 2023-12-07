package com.tco.game;

public class Square {
    private BoardPiece piece;
    private Position position;

    public Square(Position position) {
        this.position = position;
        this.piece = null;
    }

    public Square(Position position, BoardPiece piece) {
        this.position = position;
        this.piece = piece;
    }

    public BoardPiece getPiece() {
        return this.piece;
    }

    public void setPiece(BoardPiece piece) {
        this.piece = piece;
    }

    public void clearPiece() {
        this.piece = null;
    }

    public Position getPosition() {
        return this.position;
    }
}
