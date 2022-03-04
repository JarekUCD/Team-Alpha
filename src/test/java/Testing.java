//Team name: alpha, Student Numbers: 20377771 20709465 20394476
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    private Board board;
    private Setup s;
    private Player player1;
    private Player player2;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {

    // Initialises board and 2 players
    board = new Board();
    s = new Setup();
    s.setupPieces();
    player2 = new Player('O');
    player1 = new Player('X');
    player1.setPieces(s.getBlocks());
    player2.setPieces(s.getBlocks());
    }

    //Tests for isMoveValid() after the first moves
    @org.junit.jupiter.api.Test
    void isMoveValidTest() {
        board.place(9, 4, board.findBlockByName("I5", player1.getPieces()), player1);
        player1.getPieces().remove(board.findBlockByName("I5", player1.getPieces()));
        board.place(4, 9, board.findBlockByName("V5", player2.getPieces()), player2);
        player2.getPieces().remove(board.findBlockByName("V5", player2.getPieces()));

        assertFalse(board.isMoveValid(13, 4, board.findBlockByName("I1", player1.getPieces()), player1, false));
        assertFalse(board.isMoveValid(9, -1, board.findBlockByName("I1", player1.getPieces()), player1, false));
        assertFalse(board.isMoveValid(9, 6, board.findBlockByName("O4", player1.getPieces()), player1, false));
        assertFalse(board.isMoveValid(10, 10, board.findBlockByName("O4", player1.getPieces()), player1, false));
        assertFalse(board.isMoveValid(6, 10, board.findBlockByName("O4", player1.getPieces()), player1, false));
        //assertTrue(board.isMoveValid(7, 10, board.findBlockByName("O4", player1.getPieces()), player1, false));
        assertFalse(board.isMoveValid(3, 12, board.findBlockByName("I4", player1.getPieces()), player1, false));
    }

    //Tests for isMoveValid() when it is the player's first move
    @org.junit.jupiter.api.Test
    void isFirstMoveValidTest() {
        assertFalse(board.isMoveValid(13, 4, board.findBlockByName("I1", player1.getPieces()), player1, true));
        assertFalse(board.isMoveValid(9, -1, board.findBlockByName("I1", player1.getPieces()), player1, true));
        assertFalse(board.isMoveValid(7, 10, board.findBlockByName("O4", player1.getPieces()), player1, true));
        assertFalse(board.isMoveValid(9, 4, board.findBlockByName("I5", player1.getPieces()), player1, true));
        //assertTrue(board.isMoveValid(4, 9, board.findBlockByName("I5", player1.getPieces()), player1, true));
    }

    //Tests the flip method for pieces
    @org.junit.jupiter.api.Test
    void flipTest() {
        Block test = board.findBlockByName("P", s.getBlocks());
        test.flip();
        String expected =   "  A @ \n" +
                            "  A A \n" +
                            "    A \n";
        assertEquals(expected, test.printShape());
    }

    //Tests rotation of pieces
    @org.junit.jupiter.api.Test
    void rotateTest() {
        Block test = board.findBlockByName("T5", s.getBlocks());
        test.rotate(1);
        String expected =   "A     \n" +
                            "@ A A \n" +
                            "A     \n";
        assertEquals(expected, test.printShape());

        test.rotate(1);
        expected =  "A @ A \n" +
                    "  A   \n" +
                    "  A   \n";
        assertEquals(expected, test.printShape());

        test.rotate(1);
        expected =  "    A \n" +
                    "A A @ \n" +
                    "    A \n";
        assertEquals(expected, test.printShape());

        test.rotate(1);
        expected =  "  A   \n" +
                    "  A   \n" +
                    "A @ A \n";
        assertEquals(expected, test.printShape());
    }

    }