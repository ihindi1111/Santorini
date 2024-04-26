package godcards;

import components.Tile;
import components.Worker;
import components.Board;
import interfaces.IBuildStrategy;

public class Hephaestus implements IBuildStrategy {
    private boolean firstBuild = false;
    private Tile previousTile = null;

    @Override
    public boolean performBuild(Worker worker, Board board, int x, int y) {
        if (isValidBuild(worker, board, x, y)) {  // Only add a block if it won't create a dome
            previousTile.build();  // Add another block
            return true;
        }
        return false;
    }

    @Override
    public boolean isValidBuild(Worker worker, Board board, int x, int y) {
        if (board.getTile(x, y) == previousTile) {
            return true; // Skip the second build and end the building phase
        } //might need fixing
        else if (x != previousTile.getX() && y != previousTile.getY()) return false;  // Must build on the same tile twice
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
