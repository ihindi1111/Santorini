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
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidBuild(Worker worker, Board board, int x, int y) {
        Tile buildTile = board.getTile(x, y);

        // If the player is clicking on the same tile again, it's a choice to skip the second build
        if (buildTile == previousTile) {
            return true; // Skip the second build and end the building phase
        } //might need fixing
        
        if (buildTile.getX() == previousTile.getX() && buildTile.getY() == previousTile.getY()) return false;
        if (board.getTile(x, y) == null) return false; // Out of bounds
        if (x < 0 || x >= board.getBOARD_SIZE() || y < 0 || y >= board.getBOARD_SIZE()) return false; // Out of bounds
        int deltaX = Math.abs(worker.getX() - x);
        int deltaY = Math.abs(worker.getY() - y);
        if ((deltaX + deltaY) != board.getADJACENT_LIMIT()) return false; //Not nearby
        return true;
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