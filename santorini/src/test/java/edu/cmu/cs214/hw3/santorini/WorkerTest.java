package edu.cmu.cs214.hw3.santorini;

import org.junit.jupiter.api.Test;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest {

    private Worker worker;

    @Before
    public void setUp() {
        worker = new Worker(2, 3);
    }

    @Test
    public void testInitialCoordinates() {
        assertEquals(2, worker.getX());
        assertEquals(3, worker.getY());
    }

    @Test
    public void testSetX() {
        worker.setX(4);
        assertEquals(4, worker.getX());
        // Ensure Y-coordinate remains unchanged
        assertEquals(3, worker.getY());
    }

    @Test
    public void testSetY() {
        worker.setY(5);
        assertEquals(5, worker.getY());
        // Ensure X-coordinate remains unchanged
        assertEquals(2, worker.getX());
    }

    @Test
    public void testMove() {
        worker.setX(1);
        worker.setY(1);
        assertEquals(1, worker.getX());
        assertEquals(1, worker.getY());
    }
}
