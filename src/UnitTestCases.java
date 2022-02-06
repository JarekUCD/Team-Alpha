//Team name: alpha, Student Numbers: 20377771 20709465 20394476

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTestCases {

    //NOT WORKING CORRECTLY AS YET - 06-02-22 - NEED TO FIGURE OUT HOW TO RUN SETUP CORRECTLY FOR TEST CASES TO INITIALISE CORRECTLY.

    // This test checks whether user input out of bounds causes issues.
    @Test
    public void playerInputOutOfBounds(){
        Board inputTest = new Board();
        Player p1 = new Player('b');
        assertEquals(false, inputTest.isMoveValid(14, 5, 0, "i5", p1, false));
        assertEquals(false, inputTest.isMoveValid(-1, 5, 0, "i5", p1, false));
        assertEquals(false, inputTest.isMoveValid(5, -1, 0, "i5", p1, false));
        assertEquals(false, inputTest.isMoveValid(10, 25, 0, "i5", p1, false));
    }

    //This specifically tests for invalid first moves.
    @Test
    public void invalidFirstMove(){
        Board inputTest = new Board();
        Setup newSetup = new Setup();
        newSetup.setupPieces();
        Player p1 = new Player('b');
        assertEquals(false, inputTest.isMoveValid(3, 5, 0, "i1", p1, true));
        assertEquals(false, inputTest.isMoveValid(8, 2, 0, "i1", p1, true));
        assertEquals(false, inputTest.isMoveValid(7, 9, 0, "i1", p1, true));
        assertEquals(false, inputTest.isMoveValid(10, 2, 0, "i1", p1, true));
        assertEquals(true, inputTest.isMoveValid(4, 4, 0, "i3", p1, true));
        assertEquals(true, inputTest.isMoveValid(9, 4, 0, "i1", p1, true));
    }


}
