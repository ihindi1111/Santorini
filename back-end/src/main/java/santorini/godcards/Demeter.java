package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;

public class Demeter implements IBuildStrategy {
    private boolean firstBuild = false;

    @Override
    public boolean performBuild(Board board, Tile previousTile, int x, int y) {
        if (isValidBuild(board, previousTile, x, y)) {
            buildTile.build();
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidBuild(Board board, Tile previousTile, int x, int y) {
        Tile buildTile = board.getTile(x, y);

        // If the player is clicking on the same tile again, it's a choice to skip the second build
        if (buildTile == previousTile) {
            return true; // Skip the second build and end the building phase
        } //might need fixing
        
        if (buildTile.getX() == previousTile.getX() && buildTile.getY() == previousTile.getY()) return false;
        if (getTile(buildX, buildY) == null) return false; // Out of bounds
        if (buildX < 0 || buildX >= BOARD_SIZE || buildY < 0 || buildY >= BOARD_SIZE) return false; // Out of bounds
        int deltaX = Math.abs(worker.getX() - buildX);
        int deltaY = Math.abs(worker.getY() - buildY);
        if ((deltaX + deltaY) != ADJACENT_LIMIT) return false; //Not nearby
        return true;
    }

    public boolean firstBuild() {
        return firstBuild;
    }

    public void setFirstBuild(boolean firstBuild) {
        this.firstBuild = firstBuild;
    }
}