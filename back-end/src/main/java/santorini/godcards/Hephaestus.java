package godcards;

import components.Tile;
import components.Worker;
import components.Board;
import components.Player;

import interfaces.GodStrategy;

public class Hephaestus implements GodStrategy {
    private boolean firstBuild = false;
    private Tile previousTile = null;
    
    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        
        Tile buildTile = board.getTile(x, y);
    
        // Check if out of bounds, null tile, or if it's a dome (build not allowed)
        if (buildTile == null || buildTile.hasDome() ||
            x < 0 || x >= board.getBOARD_SIZE() ||
            y < 0 || y >= board.getBOARD_SIZE()) {
            return false;
        }
    
        // First build check: Must be valid according to board's rules
        if (!hasPerformedFirstAction()) {
            return board.isValidBuild(player, worker, x, y);
        } else {
            if (x == worker.getX() && y == worker.getY()) {
                return true; // Passing the second build
            }
            
            // Second build can only be on the same tile as the first build
            return buildTile == previousTile && !buildTile.hasDome(); // Check it won't create a dome
        }
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        if (isValidAction(player, worker, board, x, y)) {  // Only add a block if it won't create a dome
            if (!(x == worker.getX() && y == worker.getY())) {
                board.getTile(x, y).build(); // Only build if it's not a pass
            }
            if (!hasPerformedFirstAction()) {
                firstBuild = true;
                previousTile = board.getTile(x, y);  // Remember the tile of the first build
            } else {
                resetAfterBuild();
            }
            return true;
        }
        return false;
    }

    private void resetAfterBuild() {
        firstBuild = false;
        previousTile = null;
        // Optionally, trigger phase change or player switch here if needed
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