package com.tco.game;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean equals(Position pos) {
        if (this.row == pos.getRow() && this.col == pos.getCol()) return true;
        else return false;
    }

}
