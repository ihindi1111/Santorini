package godcards;

import components.Board;
import components.Tile;
import components.Worker;

import components.Player;
import interfaces.GodStrategy;

public class Artemis implements GodStrategy {
    private Tile initialTile = null;
    private boolean firstMoveDone = false;

    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        Tile targetTile = board.getTile(x, y);
        // Ensure movement is only horizontal or vertical and exactly one tile away
        int dx = Math.abs(worker.getX() - x);
        int dy = Math.abs(worker.getY() - y);
        if ((dx == 1 && dy == 0) || (dy == 1 && dx == 0)) {
            // Check if trying to pass by moving to the same initial tile on second move
            if (firstMoveDone && x == worker.getX() && y == worker.getY()) {
                return true; // Allow "passing" by clicking on the same tile
            }
            return !targetTile.isOccupied() && !targetTile.hasDome();
        }
        return false;
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        if (isValidAction(player, worker, board, x, y)) {
            Tile fromTile = board.getTile(worker.getX(), worker.getY());
            Tile toTile = board.getTile(x, y);
            // Move worker to new tile or stay on the same tile to "pass"
            fromTile.setWorker(null);
            toTile.setWorker(worker);
            worker.setPosition(x, y);

            if (!firstMoveDone) {
                firstMoveDone = true;
                initialTile = fromTile; // Save initial tile to prevent returning on second move
            } else {
                firstMoveDone = false; // Reset after the optional second move
                initialTile = null;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPerformedFirstAction() {
        return firstMoveDone;
    }

    @Override
    public boolean hasSecondAction() {
        return true; // Allow a second action only if the first move has been made
    }

    @Override
    public int hasNum() {
        return 1; // Artemis has a move action
    }
}