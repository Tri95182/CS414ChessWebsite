package com.tco.game;
import java.lang.Math;
import java.util.ArrayList;

public class Bishop extends BoardPiece {
    Bishop(Position position, PieceType pieceType, Color pieceColor) {
        super(position, pieceType, pieceColor);
    }

    Bishop(PieceType pieceType, Color pieceColor) {
        super(pieceType, pieceColor);
    }

    public ArrayList<int[]> getPossibleMoves() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int pcCol = this.getPosition().getCol();
        int pcRow = this.getPosition().getRow();
        int colDif, rowDif;

        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                colDif = Math.abs(col - pcCol);
                rowDif = Math.abs(row - pcRow);

                if (colDif == rowDif) {
                    if (colDif != 0 && rowDif != 0) {
                        int[] space = {row, col};
                        moves.add(space);
                    }
                }
            }
        }

        return moves;
    }
}
