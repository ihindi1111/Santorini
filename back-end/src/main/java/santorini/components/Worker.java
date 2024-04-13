package components;

public class Worker {
    private int x;
    private int y;
    private int player;

    /**
    * Constructor that initializes a worker at the specified coordinates
    * @param x The initial x-coordinate of the worker
    * @param y The initial y-coordinate of the worker
    */
    public Worker(int player, int x, int y) {
        this.x = x;
        this.y = y;
        this.player = player;
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
    * Sets the worker's position on the board.
    * @param x The new x-coordinate of the worker.
    * @param y The new y-coordinate of the worker.
    */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * 
     * @return The player object representing the owner of the worker.
     */
    public int getPlayer() {
        return this.player;
    }

    /**
     * Checks if the worker has been placed on the board
     * @return true if the worker has been placed, false otherwise
     */
    public boolean isPlaced() {
        // A worker is considered placed if its coordinates are not at the initial -1 value
        return this.x != -1 && this.y != -1;
    }
}
