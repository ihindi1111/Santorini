package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;
import interfaces.IMoveStrategy;
import components.Board;

public class Minotaur implements IMoveStrategy {

    @Override
    public boolean isValidMove(Worker worker, Tile fromTile, Tile toTile, Board board) {
        if (toTile.isOccupied() && !toTile.hasDome()) {
            // Calculate the tile behind the opponent's worker to check if it is free
            int deltaX = toTile.getX() - fromTile.getX();
            int deltaY = toTile.getY() - fromTile.getY();
            Tile behindTile = board.getTile(toTile.getX() + deltaX, toTile.getY() + deltaY);
            return behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome();
        }
        return !toTile.isOccupied() && !toTile.hasDome();
    }

    @Override
    public boolean performMove(Worker worker, int x, int y, Board board) {
        // Get the target tile using x, y coordinates
        Tile toTile = board.getTile(x, y);

        // Assume fromTile is the current worker's tile
        Tile fromTile = board.getTile(worker.getX(), worker.getY());

        // Check if there is an opponent worker at the destination tile
        if (toTile.isOccupied()) {
            Worker opponent = toTile.getWorker();
            int deltaX = x - fromTile.getX();
            int deltaY = y - fromTile.getY();

            // Calculate the coordinates of the tile behind the opponent based on the move direction
            Tile behindTile = board.getTile(toTile.getX() + deltaX, toTile.getY() + deltaY);

            // Check if the behind tile is valid and not blocked
            if (behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome()) {
                // Push the opponent's worker to the behindTile
                behindTile.setWorker(opponent);
                opponent.setPosition(behindTile.getX(), behindTile.getY());

                // Move the current worker to the toTile
                toTile.setWorker(worker);
                worker.setPosition(x, y);
                return true;
            } else {
                return false;
            }
        } else {
            // If no opponent is there, just move the worker normally
            toTile.setWorker(worker);
            worker.setPosition(x, y);
            return true;
        }
    }

    @Override
    public boolean hasPerformedFirstMove() {
        return false;
    }
    
    @Override
    public boolean hasSecondMove() {
        return false;
    }
}
