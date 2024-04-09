package edu.cmu.cs214.hw3.santorini.backend;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean gameWon;

    public Game() {
        newGame();
    }

    public void newGame() {
        this.board = new Board();
        this.players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            players.add(new Player(board));
        }
        this.currentPlayerIndex = 0;
        this.gameWon = false;
    }

    public boolean placeWorker(int workerNum, int x, int y) {
        
        Player player = players.get(currentPlayerIndex);
        try {
            player.placeWorker(workerNum, x, y);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error placing worker: " + e.getMessage());
            // Placement was not successful due to an invalid position.
            return false;
        }
    }

    public void switchPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    public boolean checkForWin(int workerID) {
        Player currPlayer = players.get(currentPlayerIndex);
        return board.getTile(currPlayer.getWorker(workerID).getX(), currPlayer.getWorker(workerID).getY()).getLevel() == 3;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }
    
    // Method to move a worker on the board, including checking if the move is valid and if it results in a win.
    public boolean moveWorker(int workerNum, int newX, int newY) {
        
        Player player = players.get(currentPlayerIndex);
        boolean moved = player.moveWorker(newX, newY, workerNum);
        if (moved) {
            if (checkForWin(workerNum)) {
                gameWon = true;
            } else {
                switchPlayer();
            }
        }
        return moved;
    }

    // Method to build on the board, including checking if the build is valid.
    public boolean build(int workerNum, int buildX, int buildY) {
        Player player = players.get(currentPlayerIndex);
        boolean built = player.build(buildX, buildY, workerNum);
        if (built) {
            switchPlayer();
        }
        return built;
    }

    // Getters for game state information that might be necessary for REST API responses
    public Board getBoard() {
        return board;
    }

    public List<Player> getPlayers() {
        return players;
    }
}