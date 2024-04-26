package godcards;

import components.Tile;
import components.Worker;
import components.Board;
import components.Player;

import interfaces.GodStrategy;

public class Atlas implements GodStrategy {
    private boolean domeBuilt = false;  // To track if the dome has been built

    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        Tile targetTile = board.getTile(x, y);

        // Allow building on the current tile (regular build or dome)
        return targetTile != null && !targetTile.hasDome();
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        Tile targetTile = board.getTile(x, y);

        if (isValidAction(player, worker, board, x, y)) {
            if (x == worker.getX() && y == worker.getY()) {
                // If player clicks on the worker's position after choosing to build, place a regular floor
                targetTile.build();
            } else {
                // If player chooses any other valid tile, build a dome at any level
                targetTile.placeDome();
            }
            domeBuilt = true;  // Mark that the build/dome action is done
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPerformedFirstAction() {
        return domeBuilt;
    }

    @Override
    public boolean hasSecondAction() {
        return false;  // No second action after dome is built
    }

    @Override
    public int hasNum() {
        return 2;  // Indicates the ability to perform a single build action
    }
}
