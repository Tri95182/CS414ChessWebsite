package com.tco.game;
import java.lang.Math;
import java.util.ArrayList;

public class Knight extends BoardPiece {
    Knight(Position position, PieceType pieceType, Color pieceColor) {
        super(position, pieceType, pieceColor);
    }

    Knight(PieceType pieceType, Color pieceColor) {
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
                
                if ((colDif == 1 || colDif == 2) && (rowDif == 1 || rowDif == 2) && rowDif != colDif) {
                    int[] space = {row, col};
                    moves.add(space);
                }
            }
        }

        return moves;
    }
}
