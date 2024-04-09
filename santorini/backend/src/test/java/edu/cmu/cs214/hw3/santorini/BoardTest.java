package edu.cmu.cs214.hw3.santorini;

import org.junit.jupiter.api.Test;

import edu.cmu.cs214.hw3.santorini.backend.Board;
import edu.cmu.cs214.hw3.santorini.backend.Tile;
import edu.cmu.cs214.hw3.santorini.backend.Worker;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private Worker worker;

    @Before
    public void setUp() {
        board = new Board();
        worker = new Worker(2, 2); // Assuming Worker has a constructor Worker(int x, int y)
    }

    @Test
    public void testIsValidBuild_Valid() {
        assertTrue(board.isValidBuild(worker, 2, 1));
    }

    @Test
    public void testIsValidBuild_Invalid_OutOfBounds() {
        assertFalse(board.isValidBuild(worker, 5, 5));
    }

    @Test
    public void testIsValidBuild_Invalid_SameBlock() {
        assertFalse(board.isValidBuild(worker, 2, 2));
    }

    @Test
    public void testIsValidBuild_Invalid_OccupiedOrDome() {
        // Manually setting a tile to be occupied or have a dome for the test
        Tile tile = board.getTile(2, 1);
        tile.setOccupied(true); // or tile.setHasDome(true) if such a method exists
        assertFalse(board.isValidBuild(worker, 2, 1));
    }

    @Test
    public void testIsValidMove_Valid() {
        assertTrue(board.isValidMove(worker, 2, 3));
    }

    @Test
    public void testIsValidMove_Invalid_OutOfBounds() {
        assertFalse(board.isValidMove(worker, -1, 2));
    }

    @Test
    public void testIsValidMove_Invalid_SameBlock() {
        assertFalse(board.isValidMove(worker, 2, 2));
    }

    @Test
    public void testIsValidMove_Invalid_Occupied() {
        Tile tile = board.getTile(2, 3);
        tile.setOccupied(true);
        assertFalse(board.isValidMove(worker, 2, 3));
    }

    @Test
    public void testIsValidMove_Invalid_TooHighToClimb() {
        Tile targetTile = board.getTile(2, 3);
        targetTile.increaseLevel(); // Assuming setLevel() method exists to set tile height
        targetTile.increaseLevel(); // Assuming setLevel() method exists to set tile height
        assertFalse(board.isValidMove(worker, 2, 3));
    }
}
