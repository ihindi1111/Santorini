package edu.cmu.cs214.hw3.santorini.backend;

public class Worker {
    private int x;
    private int y;

    /**
    * Constructor that initializes a worker at the specified coordinates
    * @param x The initial x-coordinate of the worker
    * @param y The initial y-coordinate of the worker
    */
    public Worker(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
    * Returns the x-coordinate of the worker
    * @return The x-coordinate
    */
    public int getX() {
        return x;
    }

    /**
    * Returns the y-coordinate of the worker
    * @return The y-coordinate
    */
    public int getY() {
        return y;
    }

    /**
    * Sets the x-coordinate of the worker
    * @param x The new x-coordinate
    */
    public void setX(int x) {
        this.x = x;
    }

    /**
    * Sets the y-coordinate of the worker
    * @param y The new y-coordinate
    */
    public void setY(int y) {
        this.y = y;
    }
}
