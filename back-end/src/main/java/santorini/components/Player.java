package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private int playerID;
    private Worker[] workers;
    private String playerName;
    private IBuildStrategy buildStrategy;
    private IMoveStrategy moveStrategy;

    /**
    * Constructor that initializes a player with a reference to the game board
    * @param board The game board
    */
    public Player(int playerID) {
        this.playerID = playerID;
        this.workers = new Worker[2];
        for (int i = 0; i < 2; i++) {
            workers[i] = new Worker(playerID, -1, 0);
        }
        this.playerName = "Player " + playerID;
    }

    /**
    * Retrieves a worker owned by the player
    * @param workerNum The number identifying the worker
    * @return The worker object
    */
    public Worker getWorker(int workerNum) {
        return this.workers[workerNum];
    }

    /**
     * Returns the player's name
     * @return The player's name
     */
    public int getPlayerID() {
        return this.playerID;
    }

    @Override
    public String toString() {
        return this.playerName;
    }

    public boolean hasMoveStrategy() {
        return moveStrategy != null;
    }

    public boolean hasBuildStrategy() {
        return buildStrategy != null;
    }

    // Getter and setter for strategies
    public IBuildStrategy getBuildStrategy() {
        return buildStrategy;
    }

    public void setBuildStrategy(IBuildStrategy strategy) {
        this.buildStrategy = strategy;
    }

    public IMoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public void setMoveStrategy(IMoveStrategy strategy) {
        this.moveStrategy = strategy;
    }

}