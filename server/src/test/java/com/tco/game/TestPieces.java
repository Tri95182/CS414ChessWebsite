package com.tco.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPieces {
    BoardPiece TestBoardPiece;
    ArrayList<int[]> tempReturnList;
    StringBuilder PossiblePosCords;
    Position testPos;
    @BeforeEach
    public void TestPiecesInit() {
        PossiblePosCords = new StringBuilder();
        testPos = new Position(4, 4);
    }

    //Double check that .name returns the correct NAME
    @Test
    @DisplayName("TestGetName")
    public void TestName() {
        TestBoardPiece = new Pawn(testPos, BoardPiece.PieceType.PAWN, BoardPiece.Color.WHITE);
        assertEquals(TestBoardPiece.name(), BoardPiece.PieceType.PAWN);

    }
    
    @Test
    @DisplayName("TestGetOpposite")
    public void TestGetOpposite() {
        Position OppPos;
        Position ExpectedPos;
        testPos = new Position(1, 1);
        ExpectedPos = new Position(6, 1);
        TestBoardPiece = new Pawn(testPos, BoardPiece.PieceType.PAWN, BoardPiece.Color.WHITE);
        OppPos = TestBoardPiece.getOpposite();
        assertTrue(OppPos.equals(ExpectedPos));
    }
        
    @Test
    @DisplayName("TestGetandSetPieceColor")
    public void TestColor() {
        TestBoardPiece = new Pawn(testPos, BoardPiece.PieceType.PAWN, BoardPiece.Color.WHITE);
        TestBoardPiece.setColor((BoardPiece.Color.BLACK));
        assertEquals(TestBoardPiece.getColor(), BoardPiece.Color.BLACK);

    }
    
    @Test
    @DisplayName("KingTest")
    public void KingTest() {
        TestBoardPiece = new King(testPos, BoardPiece.PieceType.KING, BoardPiece.Color.BLACK);
        tempReturnList = TestBoardPiece.getPossibleMoves();
        for (int[] integer : tempReturnList) {
            for (int i : integer) {
                PossiblePosCords.append(i);
            }
        }
        assertEquals(PossiblePosCords.toString(), "3334354345535455");

    }

    @Test
    @DisplayName("QueenTest")
    public void QueenTest() {
        TestBoardPiece = new Queen(testPos, BoardPiece.PieceType.QUEEN, BoardPiece.Color.BLACK);
        tempReturnList = TestBoardPiece.getPossibleMoves();
        for (int[] integer : tempReturnList) {
            for (int i : integer) {
                PossiblePosCords.append(i);
            }
        }
        assertEquals(PossiblePosCords.toString(), "000411141722242633343540414243454647535455626466717477");

    }

    @Test
    @DisplayName("KnightTest")
    public void KnightTest() {
        TestBoardPiece = new Knight(testPos, BoardPiece.PieceType.KNIGHT, BoardPiece.Color.BLACK);
        tempReturnList = TestBoardPiece.getPossibleMoves();
        for (int[] integer : tempReturnList) {
            for (int i : integer) {
                PossiblePosCords.append(i);
            }
        }
        assertEquals(PossiblePosCords.toString(), "2325323652566365");

    }

    @Test
    @DisplayName("RookTest")
    public void RookTest() {
        TestBoardPiece = new Rook(testPos, BoardPiece.PieceType.ROOK, BoardPiece.Color.BLACK);
        tempReturnList = TestBoardPiece.getPossibleMoves();
        for (int[] integer : tempReturnList) {
            for (int i : integer) {
                PossiblePosCords.append(i);
            }
        }
        assertEquals(PossiblePosCords.toString(), "041424344041424344454647546474");

    }

    @Test
    @DisplayName("BishopTest")
    public void BishopTest() {
        TestBoardPiece = new Bishop(testPos, BoardPiece.PieceType.BISHOP, BoardPiece.Color.BLACK);
        tempReturnList = TestBoardPiece.getPossibleMoves();
        for (int[] integer : tempReturnList) {
            for (int i : integer) {
                PossiblePosCords.append(i);
            }
        }
        //The string below is Bishops possible Cords placed in a string side by side. Starting with the cords with lowest X
        assertEquals(PossiblePosCords.toString(), "00111722263335535562667177");

    }

    @Test
    @DisplayName("PawnTest")
    public void PawnTest() {
        TestBoardPiece = new Pawn(testPos, BoardPiece.PieceType.PAWN, BoardPiece.Color.BLACK);
        tempReturnList = TestBoardPiece.getPossibleMoves();
        for (int[] integer : tempReturnList) {
            for (int i : integer) {
                PossiblePosCords.append(i);
            }
        }
        assertEquals(PossiblePosCords.toString(), "333435");
        
    }
    
    
}


