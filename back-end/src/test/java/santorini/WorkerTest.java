import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import components.Worker;

public class WorkerTest {
    private Worker worker;

    @Before
    public void setUp() {
        worker = new Worker(1, 0, 0);
    }

    @Test
    public void testInitialPosition() {
        assertEquals("Initial x should be 0", 0, worker.getX());
        assertEquals("Initial y should be 0", 0, worker.getY());
    }

    @Test
    public void testSetPosition() {
        worker.setPosition(1, 2);
        assertEquals("X should be updated to 1", 1, worker.getX());
        assertEquals("Y should be updated to 2", 2, worker.getY());
    }

    @Test
    public void testGetPlayer() {
        assertEquals("Player ID should be 1", 1, worker.getPlayer());
    }

    @Test
    public void testIsPlaced() {
        Worker newWorker = new Worker(1, -1, -1);
        assertFalse("Worker should not be considered placed if at -1, -1", newWorker.isPlaced());
        newWorker.setPosition(0, 0);
        assertTrue("Worker should be considered placed if moved to 0, 0", newWorker.isPlaced());
    }
}