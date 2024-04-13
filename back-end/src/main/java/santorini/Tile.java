public class Tile {
    private int x;
    private int y;
    private Worker worker;
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
        this.worker = null;
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
    * Sets the occupied status of the tile.
    * @param set A boolean value indicating whether the tile is occupied.
    */
    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    /**
    * Checks if the tile is currently occupied.
    * @return true if the tile is occupied, false otherwise.
    */
    public boolean isOccupied() {
        return this.worker != null;
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
    public boolean build() {
        if (!hasDome) {
            level++;
            if (level > 3) {
                level = 3;
                hasDome = true;
                return true;
            }
        }
        return false;
    }

    /**
     * Move the worker from this one to a new one
     * @param worker worker
     * @param workerTile worker's tile
     */
    public void moveWorkerTo(Tile destinationTile) {
        if (this.worker != null && !destinationTile.hasDome()) {
            destinationTile.setWorker(this.worker);
            this.worker = null; // Remove the worker from the current tile
        }
    }

    /**
     * Retrieves the x coordinate of the tile
     * @return the x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * Retrieves the y coordinate of the tile
     * @return the y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * Retrieves the worker on the tile
     * @return the worker or null if none
     */
    public Worker getWorker() {
        return this.worker;
    }

    /**
     * Returns a visual representation of the tile
     * @return the visual representation
     */
    public String visualRepresentation() {
        StringBuilder representation = new StringBuilder();

        if (this.worker != null) {
            // Assuming Worker class has a method to identify the player (e.g., "X" or "O")
            representation.append(this.worker.getPlayer());
        }

        // Enclose player identifier or empty space in tower representation
        representation.insert(0, "[".repeat(this.level));
        representation.append("]".repeat(this.level));

        if (this.hasDome) {
            // Place a "D" at the center for domes
            representation = new StringBuilder("[ [ [ D ] ] ]");
        }

        return representation.toString();
    }
}
