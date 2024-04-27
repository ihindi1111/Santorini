import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import components.Board;
import components.Tile;
import components.Worker;
import components.Player;


import godcards.Minotaur;
import godcards.Pan;
import godcards.Demeter;
import interfaces.GodStrategy;


public class BoardTest {
    private Board board;
    private Player mockPlayer;

    @Before
    public void setUp() {
        board = new Board();
        mockPlayer = new Player(1); // Initialization here
        mockPlayer.getWorker(1).setPosition(2, 2); // This assumes getWorker(1) and setPosition(x, y) are correctly implemented.
    }

    @Test
    public void testBoardInitialization() {
        for (int i = 0; i < board.getBOARD_SIZE(); i++) {
            for (int j = 0; j < board.getBOARD_SIZE(); j++) {
                assertNotNull("Tile at (" + i + ", " + j + ") should not be null", board.getTile(i, j));
                assertEquals("Incorrect X coordinate for tile at (" + i + ", " + j + ")", i, board.getTile(i, j).getX());
                assertEquals("Incorrect Y coordinate for tile at (" + i + ", " + j + ")", j, board.getTile(i, j).getY());
            }
        }
    }

    @Test
    public void testGetTileValidCoordinates() {
        Tile tile = board.getTile(2, 2);
        assertNotNull("Tile should not be null", tile);
        assertEquals("Expected X coordinate is 2", 2, tile.getX());
        assertEquals("Expected Y coordinate is 2", 2, tile.getY());
    }

    @Test
    public void testGetTileOutOfBounds() {
        assertNull("Expected null for out-of-bounds access", board.getTile(-1, 5));
        assertNull("Expected null for out-of-bounds access", board.getTile(5, 0));
    }

    @Test
    public void testValidMove() {
        mockPlayer.getWorker(1).setPosition(2, 3); // Replace MockWorker with your mock class

        assertTrue("Worker should be able to move to an adjacent tile", board.isValidMove(mockPlayer, mockPlayer.getWorker(1), 2, 3));
        assertFalse("Worker should not be able to move to a non-adjacent tile", board.isValidMove(mockPlayer, mockPlayer.getWorker(1), 4, 4));
    }

    @Test
    public void testValidBuild() {
        Player mockPlayer = new Player(1); // Replace MockPlayer with your mock class
        mockPlayer.getWorker(1).setPosition(2, 3); // Replace MockWorker with your mock class

        assertTrue("Worker should be able to build on an adjacent tile", board.isValidBuild(mockPlayer, mockPlayer.getWorker(1), 2, 3));
        assertFalse("Worker should not be able to build on the same tile", board.isValidBuild(mockPlayer, mockPlayer.getWorker(1), 2, 2));
    }

    @Test
    public void testConstants() {
        assertEquals("Board size should be 5", 5, board.getBOARD_SIZE());
        assertEquals("Max climb height should be 1", 1, board.getMAX_CLIMB_HEIGHT());
        assertEquals("Adjacent limit should be 1", 1, board.getADJACENT_LIMIT());
    }
}