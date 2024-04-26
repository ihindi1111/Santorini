package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;
import interfaces.IMoveStrategy;
import components.Board;

public class Apollo implements IMoveStrategy{
    @Override
    public boolean isValidMove(Worker worker, Tile fromTile, Tile toTile, Board board) {
        // Ensure movement is to an adjacent, orthogonal tile
        int dx = toTile.getX() - fromTile.getX();
        int dy = toTile.getY() - fromTile.getY();

        if (!((Math.abs(dx) == 1 && dy == 0) || (Math.abs(dy) == 1 && dx == 0))) {
            return false; // Not a valid Apollo move (not adjacent or same tile)
        }

        // Apollo can swap places with an opponent's worker
        return toTile.isOccupied() ? toTile.getWorker().getPlayer() != worker.getPlayer() : !toTile.hasDome();
    }

    @Override
    public boolean performMove(Worker worker, int x, int y, Board board) {
        Tile toTile = board.getTile(x, y);
        Tile fromTile = board.getTile(worker.getX(), worker.getY());

        if (isValidMove(worker, fromTile, toTile, board)) {
            if (toTile.isOccupied() && toTile.getWorker().getPlayer() != worker.getPlayer()) {
                // Swap positions with the opponent's worker
                Worker opponent = toTile.getWorker();
                toTile.setWorker(worker);
                fromTile.setWorker(opponent);
                opponent.setPosition(fromTile.getX(), fromTile.getY());
            } else {
                // Move to an unoccupied space
                toTile.setWorker(worker);
                fromTile.setWorker(null);
            }
            worker.setPosition(x, y);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPerformedFirstMove() {
        return false; // Not applicable for Apollo
    }
    
    @Override
    public boolean hasSecondMove() {
        return false; // Not applicable for Apollo
    }
}
