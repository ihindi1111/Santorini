package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.GodStrategy;

public class Player {
    private int playerID;
    private Worker[] workers;
    private String playerName;
    private GodStrategy godStrategy = null;

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

    public void setGodStrategy(GodStrategy godStrategy) {
        this.godStrategy = godStrategy;
    }

    public GodStrategy getGodStrategy() {
        return this.godStrategy;
    }

    public boolean hasGodStrategy() {
        return this.godStrategy != null;
    }

}