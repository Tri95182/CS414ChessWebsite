package com.tco.game;
import java.lang.Math;
import java.util.ArrayList;

public class King extends BoardPiece {
    King(Position position, PieceType pieceType, Color pieceColor) {
        super(position, pieceType, pieceColor);
    }

    King(PieceType pieceType, Color pieceColor) {
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
                
                if ((colDif == 1 || colDif == 0) && (rowDif == 1 || rowDif == 0)) {
                    if (colDif != 0 || rowDif != 0) {
                        int[] space = {row, col};
                        moves.add(space);
                    }
                }
            }
        }
        if (!this.hasMoved() && (pcRow == 0 || pcRow == 7) && pcCol == 4) moves = getCastleMoves(moves);
        return moves;
    }

    private ArrayList<int[]> getCastleMoves(ArrayList<int[]> moves) {
        int[] right = {this.getPosition().getRow(), this.getPosition().getCol() + 2};
        int[] left = {this.getPosition().getRow(), this.getPosition().getCol() - 2};
        moves.add(right);
        moves.add(left);
        return moves;
    }
}
