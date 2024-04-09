package edu.cmu.cs214.hw3.santorini;

import org.junit.jupiter.api.Test;

import edu.cmu.cs214.hw3.santorini.backend.Board;
import edu.cmu.cs214.hw3.santorini.backend.Player;
import edu.cmu.cs214.hw3.santorini.backend.Tile;
import edu.cmu.cs214.hw3.santorini.backend.Worker;

import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

        private Player player;
        private Board board;
        private Worker worker1;
        private Worker worker2;
    
        @Before
        public void setUp() {
            board = new Board();
            player = new Player(board);
            worker2 = new Worker(1, 1);
            player.placeWorker(1, 0, 0);
            player.placeWorker(2, 1, 1);
        }
    
        @Test
        public void testPlaceWorkerValidPosition() {
            try {
                player.placeWorker(1, 2, 2);
                assertTrue(board.getTile(2, 2).isOccupied());
            } catch (IllegalArgumentException e) {
                fail("Should not throw an exception for a valid position");
            }
        }
    
        @Test
        public void testMoveWorkerValidMove() {
            player.placeWorker(1, 2, 2);
            boolean result = player.moveWorker(2, 3, 1);
            assertTrue(result);
            assertTrue(board.getTile(2, 3).isOccupied());
            assertFalse(board.getTile(2, 2).isOccupied());
        }
    
        @Test
        public void testMoveWorkerInvalidMove() {
            player.placeWorker(1, 2, 2);
            boolean result = player.moveWorker(2, 4, 1); // Assuming this move is not valid
            assertFalse(result);
            assertTrue(board.getTile(2, 2).isOccupied());
        }
    
        @Test
        public void testBuildValidBuild() {
            player.placeWorker(1, 2, 2);
            boolean result = player.build(2, 3, 1);
            assertTrue(result);
            assertEquals(1, board.getTile(2, 3).getLevel());
        }
    
        @Test
        public void testBuildInvalidBuild() {
            player.placeWorker(1, 2, 2);
            player.placeWorker(2, 2, 3); // Another worker occupying the build location
            boolean result = player.build(2, 3, 1);
            assertFalse(result);
        }
        
        @Test
        public void testGetWorker() {
            assertNotNull(player.getWorker(1));
            assertEquals(worker1, player.getWorker(1));
            assertEquals(worker2, player.getWorker(2));
        }
}
