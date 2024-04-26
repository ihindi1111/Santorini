package godcards;

import components.Board;

import components.Tile;
import components.Worker;
import components.Player;
import interfaces.IBuildStrategy;

public class Demeter implements IBuildStrategy {
    private boolean firstBuild = false;
    private Tile previousTile = null;

    @Override
    public boolean performBuild(Player player, Worker worker, Board board, int x, int y) {
        if (isValidBuild(player, worker, board, x, y)) {
            board.getTile(x, y).build();
            if (!firstBuild()) {
                firstBuild = true;
                previousTile = board.getTile(x, y); // Remember the tile of the first build
            } else {
                firstBuild = false; // Reset for next turn
                previousTile = null; // Clear after second build
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidBuild(Player player, Worker worker, Board board, int x, int y) {
        Tile buildTile = board.getTile(x, y);

        // Check if out of bounds or null tile
        if (buildTile == null || x < 0 || x >= board.getBOARD_SIZE() || y < 0 || y >= board.getBOARD_SIZE()) return false;

        // If it's the first build, any adjacent tile is valid (not the same tile)
        if (!firstBuild) {
            return board.isValidBuild(player, worker, x, y);
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