package godcards;

import components.Board;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;

public class Demeter implements IBuildStrategy {
    private boolean firstBuild = false;
    private Tile previousTile = null;

    @Override
    public boolean performBuild(Worker worker, Board board, int x, int y) {
        if (isValidBuild(worker, board, x, y)) {
            board.getTile(x, y).build();
            if (!firstBuildDone) {
                firstBuildDone = true;
                previousTile = board.getTile(x, y); // Remember the tile of the first build
            } else {
                firstBuildDone = false; // Reset for next turn
                previousTile = null; // Clear after second build
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidBuild(Worker worker, Board board, int x, int y) {
        Tile buildTile = board.getTile(x, y);

        // Check if out of bounds or null tile
        if (buildTile == null || x < 0 || x >= board.getBOARD_SIZE() || y < 0 || y >= board.getBOARD_SIZE()) return false;

        // If it's the first build, any adjacent tile is valid (not the same tile)
        if (!firstBuildDone) {
            int deltaX = Math.abs(worker.getX() - x);
            int deltaY = Math.abs(worker.getY() - y);
            return (deltaX <= 1 && deltaY <= 1 && !(deltaX == 0 && deltaY == 0));
        } else {
            // For the second build, the tile must be different unless skipping
            if (buildTile == previousTile) return true; // Allow skip by clicking the same tile
            return buildTile != previousTile && !buildTile.isOccupied();
        }
    }

    public boolean firstBuild() {
        return firstBuild;
    }

    public void setFirstBuild(boolean firstBuild) {
        this.firstBuild = firstBuild;
    }

    public void setPreviousTile(Tile previousTile) {
        this.previousTile = previousTile;
    }
}