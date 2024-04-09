package edu.cmu.cs214.hw3.santorini;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Board board;
    private List<Player> players;
    private int currentPlayer;
    private boolean gameWon;

    /**
     * Constructor to initialize a new game
     */
    public Game() {
        newGame();
    }

    /**
     * Sets up a new game by initializing the board, players, and setting the game state
     */
    public void newGame() {
        this.board = new Board();
        this.players = new ArrayList<>();
        this.players.add(new Player(board));
        this.players.add(new Player(board));
        
        this.players.get(0).placeWorker(1, 1, 1);
        this.players.get(0).placeWorker(2, 1, 2);
        this.players.get(1).placeWorker(1, 3, 3);
        this.players.get(1).placeWorker(2, 3, 4);
        
        this.currentPlayer = 0;
        this.gameWon = false;
    }

    /**
     * Switches the current player to the next one in the rotation
     */
    public void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % players.size();
    }

    /**
     * Begins the game loop, allowing players to take turns until the game is won.
     */
    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (!gameWon) {
            System.out.println("Player " + (currentPlayer + 1) + "'s turn.");

            int workerID = getPlayerInputForWorkerID(scanner); //Placeholder
            int[] moveCoordinates = getPlayerInputForMove(scanner); //Placeholder
            boolean moved = players.get(currentPlayer).moveWorker(moveCoordinates[0], moveCoordinates[1], workerID);
            
            if (!moved) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            if (checkForWin(workerID)) {
                gameWon = true;
                System.out.println("Player " + (currentPlayer + 1) + " wins!");
                break;
            }

            int[] buildCoordinates = getPlayerInputForBuild(scanner);
            boolean built = players.get(currentPlayer).build(buildCoordinates[0], buildCoordinates[1], workerID);
            
            if (!built) {
                System.out.println("Invalid build. Try again.");
                continue;
            }

            switchPlayer();
        }
        scanner.close();
    }

    /**
    * Checks if placing a worker at the specified coordinates results in a win
    * @param workerID The ID of the worker being checked
    * @return true if the move wins the game, false otherwise
    */
    private boolean checkForWin(int workerID) {
        Player currPlayer = players.get(currentPlayer);
        return board.getTile(currPlayer.getWorker(workerID).getX(), currPlayer.getWorker(workerID).getY()).getLevel() == 3;
    }

    /**
    * Placeholder method for getting player input for which worker to move
    * @param scanner Scanner object for input
    * @return Worker ID
    */
    private int getPlayerInputForWorkerID(Scanner scanner) {
        int workerID;
        do {
            System.out.println("Select a worker to move (1 or 2):");
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid worker ID. Please enter 1 or 2:");
                scanner.next(); // this is important to clear the scanner's buffer
            }
            workerID = scanner.nextInt();
        } while (workerID != 1 && workerID != 2);
        return workerID;
    }

    /**
    * Placeholder method for getting player input for move coordinates.
    * @param scanner Scanner object for input.
    * @return Array of integers representing x and y coordinates.
    */
    private int[] getPlayerInputForMove(Scanner scanner, Worker worker) {
        int x, y;
        boolean validInput;
        do {
            System.out.println("Enter the coordinates to move to (format: x y):");
            x = scanner.nextInt();
            y = scanner.nextInt();
            validInput = board.isValidMove(worker, x, y);
            if (!validInput) {
                System.out.println("Invalid move. Try again.");
            }
        } while (!validInput);
        return new int[]{x, y};
    }

    /**
    * Placeholder method for getting player input for build coordinates
    * @param scanner Scanner object for input
    * @return Array of integers representing x and y coordinates
    */
    private int[] getPlayerInputForBuild(Scanner scanner, Worker worker) {
        int x, y;
        boolean validInput;
        do {
            System.out.println("Enter the coordinates to build on (format: x y):");
            x = scanner.nextInt();
            y = scanner.nextInt();
            validInput = board.isValidBuild(worker, x, y);
            if (!validInput) {
                System.out.println("Invalid build. Try again.");
            }
        } while (!validInput);
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}