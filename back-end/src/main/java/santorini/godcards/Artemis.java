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
        // Check basic movement rules: adjacent and not the same tile
        int dx = Math.abs(worker.getX() - x);
        int dy = Math.abs(worker.getY() - y);
        if ((dx == 1 && dy <= 1 || dy == 1 && dx <= 1) && (dx + dy != 0)) {
            // Additional check for Artemis: cannot move back to initial tile on second move
            if (firstMoveDone && initialTile == targetTile) {
                return false;
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
            // Move worker to new tile
            fromTile.setWorker(null);
            toTile.setWorker(worker);
            worker.setPosition(x, y);

            if (!firstMoveDone) {
                firstMoveDone = true;
                initialTile = fromTile;
            } else {
                firstMoveDone = false; // Reset after second move
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