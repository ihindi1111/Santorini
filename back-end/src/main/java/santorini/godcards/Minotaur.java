package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;
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
    public void performMove(Worker worker, Tile toTile, Board board) {
        // Assume fromTile is the current worker's tile
        Tile fromTile = board.getTile(worker.getX(), worker.getY());
        Worker opponent = toTile.getWorker();
        int deltaX = toTile.getX() - fromTile.getX();
        int deltaY = toTile.getY() - fromTile.getY();
        Tile behindTile = board.getTile(toTile.getX() + deltaX, toTile.getY() + deltaY);

        // Push the opponent's worker to the behindTile
        behindTile.setWorker(opponent);
        opponent.setPosition(behindTile.getX(), behindTile.getY());

        // Move the current worker to the toTile
        toTile.setWorker(worker);
        worker.setPosition(toTile.getX(), toTile.getY());
    }
}
