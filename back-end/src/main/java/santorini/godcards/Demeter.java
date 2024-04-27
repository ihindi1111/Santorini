package godcards;

import components.Board;

import components.Tile;
import components.Worker;
import components.Player;
import interfaces.GodStrategy;

public class Demeter implements GodStrategy {
    private boolean firstBuild = false;
    private Tile previousTile = null;

    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        Tile buildTile = board.getTile(x, y);

        // Check if out of bounds or null tile
        if (buildTile == null || x < 0 || x >= board.getBOARD_SIZE() || y < 0 || y >= board.getBOARD_SIZE()) {
            return false;
        }

        // First build check: Must be valid according to board's rules
        if (!firstBuild) {
            return board.isValidBuild(player, worker, x, y);
        } else {
            if (x == worker.getX() && y == worker.getY()) return true; // Passing the second build
            if (buildTile == previousTile) return false;
            int deltaX = Math.abs(worker.getX() - x);
            int deltaY = Math.abs(worker.getY() - y);
            if ((deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1)) return buildTile == previousTile || (buildTile != previousTile && !buildTile.isOccupied());
            return false;
        }
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        if (isValidAction(player, worker, board, x, y)) {
            if (!(x == worker.getX() && y == worker.getY())) {
                board.getTile(x, y).build(); // Only build if it's not a pass
            }
            if (!firstBuild) {
                firstBuild = true;
                previousTile = board.getTile(x, y);  // Remember the tile of the first build
            } else {
                firstBuild = false;
                previousTile = null;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPerformedFirstAction() {
        return firstBuild;
    }

    @Override
    public boolean hasSecondAction() {
        return true;
    }

    @Override
    public int hasNum() {
        return 2;
    }
}