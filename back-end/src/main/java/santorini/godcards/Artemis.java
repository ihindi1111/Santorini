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
        // Ensure movement is only horizontal or vertical and exactly one tile away, or allow staying on the same tile to "pass"
        int dx = Math.abs(worker.getX() - x);
        int dy = Math.abs(worker.getY() - y);

        // Check for the "pass" option on the second move
        if (firstMoveDone && x == worker.getX() && y == worker.getY()) {
            return true; // Allow "passing" by staying on the same tile
        }

        // Regular move validation
        if ((dx == 1 && dy == 0) || (dy == 1 && dx == 0)) {
            return !targetTile.isOccupied() && !targetTile.hasDome();
        }

        return false;
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        if (isValidAction(player, worker, board, x, y)) {
            Tile fromTile = board.getTile(worker.getX(), worker.getY());
            Tile toTile = board.getTile(x, y);
            // Move worker to new tile or allow to "pass" by staying on the same tile
            if (!(x == worker.getX() && y == worker.getY())) { // Ensure it's a move, not a pass
                fromTile.setWorker(null);
                toTile.setWorker(worker);
                worker.setPosition(x, y);
            }

            if (!firstMoveDone) {
                firstMoveDone = true;
                initialTile = fromTile; // Remember initial position for the second move
            } else {
                firstMoveDone = false; // Reset after the second move
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