package components;

import interfaces.IBuildStrategy;
import interfaces.IMoveStrategy;

public class Board {
    private static final int BOARD_SIZE = 5;
    private static final int MAX_CLIMB_HEIGHT = 1;
    private static final int ADJACENT_LIMIT = 1;
    
    
    private Tile[][] tiles = new Tile[BOARD_SIZE][BOARD_SIZE];
    /**
     * Initializes an empty board
     */
    public Board() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Retrieves the tile at the specified coordinates.
     *
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return The tile at the given coordinates, or null if the coordinates are out of bounds.
     */
    public Tile getTile(int x, int y) {
        if (x >= 0 && x < tiles.length && y >= 0 && y < tiles[x].length) {
            return tiles[x][y];
        } else {
            return null;
        }
    }

    /**
    * Checks if it is valid to build at the specified coordinates by a worker
    * @param worker The worker attempting the build
    * @param buildX The x-coordinate to build at
    * @param buildY The y-coordinate to build at
    * @return true if the build is valid, false otherwise
    */
    public boolean isValidBuild(Player player, Worker worker, int buildX, int buildY) {
        if (player.hasBuildStrategy()) return player.getBuildStrategy().isValidBuild(worker, this, buildX, buildY);
        if (worker == null) return false;
        if (getTile(buildX, buildY) == null) return false; // Out of bounds
        if (buildX < 0 || buildX >= BOARD_SIZE || buildY < 0 || buildY >= BOARD_SIZE) return false; // Out of bounds
        int deltaX = Math.abs(worker.getX() - buildX);
        int deltaY = Math.abs(worker.getY() - buildY);
        if ((deltaX + deltaY) != ADJACENT_LIMIT) return false; //Not nearby
        if (worker.getX() == buildX && worker.getY() == buildY) return false; // Worker is staying on the same block
        Tile tileToBuild = getTile(buildX, buildY);
        if (tileToBuild.isOccupied() || tileToBuild.hasDome()) return false; // Occupied or has dome
        return true;
    }

    /**
    * Checks if it is valid for a worker to move to the specified coordinates
    * @param worker The worker attempting to move
    * @param newX The x-coordinate to move to
    * @param newY The y-coordinate to move to
    * @return true if the move is valid, false otherwise
    */
    public boolean isValidMove(Player player, Worker worker, int newX, int newY) {
        // Check if move is within bounds and to an adjacent, unoccupied tile that does not exceed climb limit
        if (player.hasMoveStrategy()) return player.getMoveStrategy().isValidMove(worker, getTile(worker.getX(), worker.getY()), getTile(newX, newY), this);
        if (worker == null) return false;
        if (getTile(newX, newY) == null) return false; // Out of bounds
        if (newX < 0 || newX >= BOARD_SIZE || newY < 0 || newY >= BOARD_SIZE) return false; // Out of bounds
        if (worker.getX() == newX && worker.getY() == newY) return false; // Worker is staying on the same block
        int deltaX = Math.abs(worker.getX() - newX);
        int deltaY = Math.abs(worker.getY() - newY);
        if ((deltaX + deltaY) != ADJACENT_LIMIT) return false; //Not nearby
        if (getTile(newX, newY).isOccupied()) return false; // Occupied
        if (getTile(newX, newY).hasDome()) return false;
        if (getTile(newX, newY).getLevel() - getTile(worker.getX(), worker.getY()).getLevel() > MAX_CLIMB_HEIGHT) return false; // Too high to climb
        return true;
    }

    /**
     * 
     * 
     * @return The size of the board
     */
    public int getBOARD_SIZE() {
        return BOARD_SIZE;
    }

    /**
     * 
     * 
     * @return The maximum height a worker can climb
     */
    public int getMAX_CLIMB_HEIGHT() {
        return MAX_CLIMB_HEIGHT;
    }

    /**
     * 
     * 
     * @return The limit of adjacent tiles
     */
    public int getADJACENT_LIMIT() {
        return ADJACENT_LIMIT;
    }
}
