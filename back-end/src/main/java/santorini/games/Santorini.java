package games;

import components.Board;
import components.Worker;
import components.Player;
import components.Tile;
import components.TurnPhase;
import components.GodCards;

import plugin.SantoriniPlugin;

import interfaces.GodStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import godcards.Demeter;
import godcards.Hephaestus;
import godcards.Minotaur;
import godcards.Pan;
import godcards.Apollo;
import godcards.Artemis;
import godcards.Atlas;


public final class Santorini {
    private Board board;
    private List<Player> players;
    private Player currPlayer;
    private boolean gameWon;
    public static final int SIZE = 5;
    private TurnPhase currentPhase = TurnPhase.SELECT_GOD_CARD;
    private Worker selectedWorker;
    private GodCards godCards;
    private Tile startTile;

    /**
     * Constructor for Santorini game initializes the board and players.
     */
    public Santorini() {
        this.board = new Board();
        this.players = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            players.add(new Player(i));
        }
        currPlayer = players.get(0);
        this.gameWon = false;
        this.godCards = new GodCards();

        initializeGodCards();
    }

    private void initializeGodCards() {
        godCards.registerGodCard("Demeter", new Demeter());
        godCards.registerGodCard("Hephaestus", new Hephaestus());
        godCards.registerGodCard("Minotaur", new Minotaur());
        godCards.registerGodCard("Pan", new Pan());
        godCards.registerGodCard("Apollo", new Apollo());
        godCards.registerGodCard("Artemis", new Artemis());
        godCards.registerGodCard("Atlas", new Atlas());
    }

    
    /**
     * Switches the active player to the next one in the list.
     */
    private void switchPlayer() {
        int nextPlayerIndex = (players.indexOf(currPlayer) + 1) % players.size();
        this.currPlayer = players.get(nextPlayerIndex);
    }

    /**
     * Checks if a worker has reached the third level of a tower
     * @param worker The worker to check for a win condition
     * @return True if the worker has reached the third level, false otherwise
     */
    private boolean checkForWin(Worker worker) {
        return board.getTile(worker.getX(), worker.getY()).getLevel() == 3;
    }

    /**
     * Checks if the game has been won
     * @return True if the game has been won, false otherwise
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Returns the current player
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currPlayer;
    }

    /**
     * Moves a worker to a new position on the board
     * @param worker The worker to move
     * @param newX The x-coordinate of the new position
     * @param newY The y-coordinate of the new position
     * @return True if the move was successful, false otherwise
     */
    private boolean moveWorker(Worker worker, int newX, int newY) {
        if (board.isValidMove(currPlayer, worker, newX, newY)) {
            board.getTile(worker.getX(), worker.getY()).setWorker(null);
            Tile moving = board.getTile(newX, newY);
            moving.setWorker(worker);
            worker.setPosition(newX, newY);
            return true;
        }
        return false;
    }

    /**
     * Method to build on the board, including checking if the build is valid.
     * @param worker The worker attempting the build
     * @param buildX The x-coordinate to build at
     * @param buildY The y-coordinate to build at
     * @return true if the build is valid, false otherwise
     */
    private boolean build(Worker worker, int buildX, int buildY) {
        if (board.isValidBuild(currPlayer, worker, buildX, buildY)) {
            Tile tileToBuild = board.getTile(buildX, buildY);
            tileToBuild.build();
            switchPlayer();
            return true;
        }
        return false;
    }

    /**
    * Places a worker at the specified coordinates on the board
    * @param workerNum The number identifying the worker
    * @param x The x-coordinate for the worker placement
    * @param y The y-coordinate for the worker placement
    */
    private boolean placeWorker(Worker worker, int x, int y) {
        Tile tile = board.getTile(x, y);
        if (tile != null && !tile.isOccupied()) {
            tile.setWorker(worker);
            worker.setPosition(x, y);
            return true; // Successfully placed the worker.
        } else {
            return false; // Failed to place the worker due to the tile being occupied or out of bounds.
        }
    }

    /**
     * Returns the board for the game
     * @return The game board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Returns the players in the game
     * @return The list of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Selects a worker at the specified coordinates
     * @param x The x-coordinate of the worker
     * @param y The y-coordinate of the worker
     * @return The worker at the specified coordinates, or null if no worker is present
     */
    public Worker selectWorker(int x, int y) {
        Tile tile = board.getTile(x, y);
        if (tile != null && tile.getWorker() != null && tile.getWorker().getPlayer() == currPlayer.getPlayerID()) {
            return tile.getWorker();
        }
        return null;
    }

    /**
     * Returns the visual representation of a tile at the specified coordinates
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     * @return The visual representation of the tile
     */
    public String visualRepresentation(int x, int y) {
        return board.getTile(x, y).visualRepresentation();
    }

    /**
     * Returns the current phase of the game
     * @return The current phase of the game
     */
    public TurnPhase getPhase() {
        return currentPhase;
    }

    /**
     * Handles the placement of workers on the board
     * @param x The x-coordinate of the placement
     * @param y The y-coordinate of the placement
     */
    private void handleWorkerPlacement(int x, int y) {
        if (!currPlayer.getWorker(0).isPlaced()) {
            // Place the first worker if not already placed
            boolean placed = placeWorker(currPlayer.getWorker(0), x, y);
            if (!placed) {
                // If the placement was not successful, perhaps display an error or log it
                return;
            }
        } else if (!currPlayer.getWorker(1).isPlaced()) {
            // Place the second worker if the first one is already placed
            boolean placed = placeWorker(currPlayer.getWorker(1), x, y);
            if (!placed) {
                // If the placement was not successful, perhaps display an error or log it
                return;
            }
            // After the second worker is placed for the current player, switch to the next player
            switchPlayer();
        }
    
        // Check if all players have placed their workers before moving to the MOVE phase for the game
        if (allWorkersPlaced()) {
            currentPhase = TurnPhase.SELECT_WORKER;
        }
    }

    /**
     * Checks if all workers have been placed on the board
     * @return True if all workers have been placed, false otherwise
     */
    private boolean allWorkersPlaced() {
        for (Player player : players) {
            if (player.getWorker(0).getX() == -1 || player.getWorker(1).getX() == -1) {
                return false;
            }
        }
        return true;
    }

    private void handleGodAction(Worker worker, int x, int y) {
        GodStrategy godStrategy = currPlayer.getGodStrategy();
        if (!godStrategy.hasPerformedFirstAction() && !godStrategy.hasSecondAction()) {
            System.out.println("Meow");
            if (godStrategy.performAction(currPlayer, worker, board, x, y)) {
                System.out.println("Yay");
                if (checkForWin(worker)) {
                    gameWon = true;
                    return;
                }

                if (godStrategy.hasNum() == 1) currentPhase = TurnPhase.BUILD;
                else if (godStrategy.hasNum() == 2) {
                    switchPlayer();
                    currentPhase = TurnPhase.SELECT_WORKER;
                }
            }
        }
        else if (!godStrategy.hasPerformedFirstAction() && godStrategy.hasSecondAction()) {
            if (godStrategy.performAction(currPlayer, worker, board, x, y)) {
                if (checkForWin(worker)) {
                    gameWon = true;
                    return;
                }
            }
        }
        else if (godStrategy.hasPerformedFirstAction() && godStrategy.hasSecondAction()) {
            if (godStrategy.performAction(currPlayer, worker, board, x, y)) {
                if (checkForWin(worker)) {
                    gameWon = true;
                    return;
                }
                if (godStrategy.hasNum() == 1) currentPhase = TurnPhase.BUILD;
                else if (godStrategy.hasNum() == 2) {
                    switchPlayer();
                    currentPhase = TurnPhase.SELECT_WORKER;
                }
            }
        }
        else return;
    }

    private boolean godCardsSelected() {
        for (Player player : players) {
            if (!player.hasGodStrategy()) {
                return false;
            }
        }
        return true;
    }

    public void handleGodChoice(int x, int y) {
        // Convert coordinates to a linear index assuming a row-major order
        int index = y * SIZE + x;
        String godCardName = godCards.getCardAtPosition(index);
        if (godCardName != null && godCards.isValidSelection(godCardName)) {
            GodStrategy strategy = godCards.getStrategy(godCardName);
            if (strategy != null) {
                currPlayer.setGodStrategy(strategy);
                switchPlayer();
            }
            if (godCardsSelected()) {  // This checks if all players have selected a god card
                currentPhase = TurnPhase.PLACE_WORKERS;
            }
        }
    }

    /**
     * Plays the game based on the current phase and the coordinates of the move
     * @param x The x-coordinate of the move
     * @param y The y-coordinate of the move
     */
    public void play(int x, int y) {
        if (gameWon) {
            return; // Stop processing if the game is already won.
        }
        GodStrategy godStrategy = currPlayer.getGodStrategy();
        switch (currentPhase) {
            case SELECT_GOD_CARD:
                handleGodChoice(x, y);
                break;
            case PLACE_WORKERS:
                handleWorkerPlacement(x, y);
                break;
            case SELECT_WORKER:
                selectedWorker = selectWorker(x, y);
                if (selectedWorker != null && selectedWorker.getPlayer() == currPlayer.getPlayerID()) {
                    startTile = board.getTile(selectedWorker.getX(), selectedWorker.getY());
                    currentPhase = TurnPhase.MOVE;
                }
                break;
            case MOVE:
                int x1 = selectedWorker.getX();
                int y1 = selectedWorker.getY();
                if (godStrategy.hasNum() == 1 && godStrategy.hasSecondAction()) handleGodAction(selectedWorker, x, y);
                else if (godStrategy.hasNum() == 1) {
                    if (godStrategy.performAction(currPlayer, selectedWorker, board, x, y)) {
                        if (checkForWin(selectedWorker)) {
                            gameWon = true;
                            return;
                        }
                        currentPhase = TurnPhase.BUILD;
                    }
                }
                else if (selectedWorker != null && moveWorker(selectedWorker, x, y)) {
                    if (godStrategy.hasNum() == 0) {
                        if (godStrategy.performAction(currPlayer, selectedWorker, board, x1, y1)) {
                            gameWon = true;
                            return;
                        }
                        if (checkForWin(selectedWorker)) {
                            gameWon = true;
                            return;
                        }
                    }
                    if (checkForWin(selectedWorker)) {
                        gameWon = true;
                        return;
                    }
                    currentPhase = TurnPhase.BUILD;
                }
                else {
                    selectedWorker = null;  // Move was not successful, reset the selected worker
                    currentPhase = TurnPhase.SELECT_WORKER;  // Optionally reset the phase or handle error
                }
                break;
            case BUILD:
                if (godStrategy.hasNum() == 2) handleGodAction(selectedWorker, x, y);
                else if (selectedWorker != null && build(selectedWorker, x, y)) {
                    selectedWorker = null;
                    currentPhase = TurnPhase.SELECT_WORKER;
                }
                break;
            default:
                break;
        }
    }
    
    /**
     * Returns the selected worker
     * @return The selected worker
     */
    public Worker getSelectedWorker() {
        return selectedWorker;
    }

    public GodCards getGodCards() {
        return godCards;
    }
}
// private void handleGodMove(Worker worker, int x, int y) {
    //     boolean placed = currPlayer.getMoveStrategy().performMove(worker, x, y, board);
    //     IMoveStrategy moveStrategy = currPlayer.getMoveStrategy();
    //     if (!moveStrategy.hasSecondMove()) {
    //         if (placed) {
    //             currPlayer.getMoveStrategy().performMove(worker, x, y, board);
    //             checkForWin(worker);
    //             currentPhase = TurnPhase.BUILD;
    //         }
    //     }
    //     else if (moveStrategy.hasSecondMove() && !moveStrategy.hasPerformedFirstMove()) {
    //         if (placed) {
    //             currPlayer.getMoveStrategy().performMove(worker, x, y, board);
    //             checkForWin(worker);
    //         }
    //     }
    //     else if (currPlayer.getMoveStrategy().hasSecondMove() && moveStrategy.hasPerformedFirstMove()) {
    //         if (placed) {
    //             currPlayer.getMoveStrategy().performMove(worker, x, y, board);
    //             checkForWin(worker);
    //             currentPhase = TurnPhase.BUILD;
    //         }
    //     }
    //     else currentPhase = TurnPhase.BUILD;
    // }

    // private void handleGodBuild(Worker worker, int x, int y) {
    //     IBuildStrategy buildStrategy = currPlayer.getBuildStrategy();
    //     if (!buildStrategy.firstBuild()) {
    //         if (selectedWorker != null && buildStrategy.performBuild(currPlayer, worker, board, x, y)) return;
    //     }
    //     else {
    //         boolean built = currPlayer.getBuildStrategy().performBuild(currPlayer, worker, board, x, y);
    //         if (built) {
    //             switchPlayer();
    //             currentPhase = TurnPhase.SELECT_WORKER;
    //         }
    //     }
    // }