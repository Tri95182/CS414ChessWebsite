package com.tco.misc;


import com.tco.game.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//what Initialized Board Looks like

public class TestBoard {
    private Board testBoard;

    @BeforeEach
    public void boardConfigInit() {
        testBoard = new Board();
    }

    //Make sure its Initiated Correctly.
    @Test
    @DisplayName("TestBoardInit")
    public void BoardInit() {
        String InitizedBoard = "ROOKKNIGHTBISHOPQUEENKINGBISHOPKNIGHTROOKPAWNPAWNPAWNPAWNPAWNPAWNPAWNPAWN                                PAWNPAWNPAWNPAWNPAWNPAWNPAWNPAWNROOKKNIGHTBISHOPQUEENKINGBISHOPKNIGHTROOK";
        assertEquals(InitizedBoard, testBoard.toString());
    }

}
