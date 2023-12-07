package com.tco.game;
import java.lang.Math;
import java.util.ArrayList;

public class Pawn extends BoardPiece {
    Pawn(Position position, PieceType pieceType, Color pieceColor) {
        super(position, pieceType, pieceColor);
    }

    Pawn(PieceType pieceType, Color pieceColor) {
        super(pieceType, pieceColor);
    }

    public ArrayList<int[]> getPossibleMoves() {
        if (this.getColor() == Color.WHITE) {
            return getWhiteMoves();
        } else {
            return getBlackMoves();
        }
    }

    private ArrayList<int[]> getWhiteMoves() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int pcCol = this.getPosition().getCol();
        int pcRow = this.getPosition().getRow();
        int colDif;

        for (int col = 0; col < 8; col++) {
            colDif = col - pcCol;
            if (colDif > -2 && colDif < 2) {
                int[] space = {pcRow + 1, col};
                moves.add(space);
            }
            if (colDif == 0 && pcRow == 1) {
                int[] space = {pcRow + 2, col};
                moves.add(space);
            }
        }

        return moves;
    }

    private ArrayList<int[]> getBlackMoves() {
        ArrayList<int[]> moves = new ArrayList<int[]>();
        int pcCol = this.getPosition().getCol();
        int pcRow = this.getPosition().getRow();
        int colDif;
        
        for (int col = 0; col < 8; col++) {
            colDif = col - pcCol;
            if (colDif > -2 && colDif < 2) {
                int[] space = {pcRow - 1, col};
                moves.add(space);
            }
            if (colDif == 0 && pcRow == 6) {
                int[] space = {pcRow - 2, col};
                moves.add(space);
            }
        }

        return moves;
    }
}
