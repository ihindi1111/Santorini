package edu.cmu.cs214.hw3.santorini.backend;

public class Tile {
    private int x;
    private int y;
    private boolean isOccupied;
    private boolean hasDome;
    private int level;

    /**
    * Constructor that initializes a tile with the specified coordinates, not occupied, without a dome, and at level 0.
    * @param x The x-coordinate of the tile.
    * @param y The y-coordinate of the tile.
    */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        this.isOccupied = false;
        this.hasDome = false;
        this.level = 0;
    }

    /**
    * Returns the current level of the tile.
    * @return The level of the tile.
    */
    public int getLevel() {
        return level;
    }

    /**
    * Increases the level of the tile by one, up to a maximum of 3. When the tile reaches level 3, a dome is added.
    */
    public void increaseLevel() {
        if (!hasDome) {
            level++;
            if (level > 3) {
                level = 3;
                hasDome = true;
            }
        }
    }

    /**
    * Sets the occupied status of the tile.
    * @param set A boolean value indicating whether the tile is occupied.
    */
    public void setOccupied(boolean set) {
        this.isOccupied = set;
    }

    /**
    * Checks if the tile is currently occupied.
    * @return true if the tile is occupied, false otherwise.
    */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
    * Checks if the tile has a dome.
    * @return true if the tile has a dome, false otherwise.
    */
    public boolean hasDome() {
        return hasDome;
    }

    /**
     * Performs a build, increasing level
     */
    public void build() {
        increaseLevel();
    }

    /**
     * Move the worker from its previous tile to the new one
     * @param worker worker
     * @param workerTile worker's tile
     */
    public void moveWorker(Worker worker, Tile workerTile) {
        workerTile.setOccupied(false);
        worker.setX(this.x);
        worker.setY(this.y);
        this.isOccupied = true;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
