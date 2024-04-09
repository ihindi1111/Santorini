package edu.cmu.cs214.hw3.santorini.backend;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private Board board;
    private List<Player> players;
    private Player currPlayer;
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
        currPlayer = players.get(0);
        this.gameWon = false;
    }

    public boolean placeWorker(int workerNum, int x, int y) {
        
        Player player = currPlayer;
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
        int nextPlayerIndex = (players.indexOf(currPlayer) + 1) % players.size();
        this.currPlayer = players.get(nextPlayerIndex);
    }

    public boolean checkForWin(int workerID) {
        return board.getTile(currPlayer.getWorker(workerID).getX(), currPlayer.getWorker(workerID).getY()).getLevel() == 3;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public Player getCurrentPlayer() {
        return currPlayer;
    }
    
    // Method to move a worker on the board, including checking if the move is valid and if it results in a win.
    public boolean moveWorker(int workerNum, int newX, int newY) {
        boolean moved = currPlayer.moveWorker(newX, newY, workerNum);
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
        boolean built = currPlayer.build(buildX, buildY, workerNum);
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