package edu.cmu.cs214.hw3.santorini.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private Board board;
    private Map<Integer, Worker> workers = new HashMap<>();

    /**
    * Constructor that initializes a player with a reference to the game board
    * @param board The game board
 */
    public Player(Board board) {
        this.board = board;
        this.workers = new HashMap<>();
        for (int i = 1; i <= 2; i++) {
            workers.put(i, new Worker(-1, -1)); // Temporary coordinates, indicating unplaced workers
        }
    }

    /**
    * Places a worker at the specified coordinates on the board
    * @param workerNum The number identifying the worker
    * @param x The x-coordinate for the worker placement
    * @param y The y-coordinate for the worker placement
    */
    public void placeWorker(int workerNum, int x, int y) {
        Worker worker = workers.get(workerNum);
        Tile tile = board.getTile(x, y);
        if (!tile.isOccupied()) {
            tile.setOccupied(true);
            worker.setX(x);
            worker.setY(y);
        } else {
            throw new IllegalArgumentException("Worker Position is out of bounds");
        }
    }

    /**
    * Moves the specified worker to the given coordinates
    * @param x The x-coordinate to move the worker to
    * @param y The y-coordinate to move the worker to
    * @param workerNum The number identifying the worker to move
    * @return true if the move was successful, false otherwise
    */
    public boolean moveWorker(int x, int y, int workerNum) {
        Worker worker = workers.get(workerNum);
        if (worker != null && board.isValidMove(worker, x, y)) {
            Tile newTile = board.getTile(x, y);
            newTile.moveWorker(worker, board.getTile(worker.getX(), worker.getY()));
            return true;
        } else {
            return false;
        }
    }

    /**
    * Retrieves a worker owned by the player
    * @param workerNum The number identifying the worker
    * @return The worker object
    */
    public Worker getWorker(int workerNum) {
        return workers.get(workerNum);
    }

    /**
    * Instructs a worker to build at the specified coordinates.
    * @param x The x-coordinate to build at.
    * @param y The y-coordinate to build at.
    * @param workerID The ID of the worker who will build.
    * @return true if the build action was successful, false otherwise.
    */
    public boolean build(int x, int y, int workerID) {
        Worker worker = workers.get(workerID);
        if (worker != null && board.isValidBuild(worker, x, y)) {
            Tile tileToBuild = board.getTile(x, y);
            tileToBuild.build();
            return true;
        } else {
            return false;
        }
    }

}