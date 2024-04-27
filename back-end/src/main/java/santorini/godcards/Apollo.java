package godcards;

import components.Tile;
import components.Worker;
import components.Board;
import components.Player;

import interfaces.GodStrategy;

public class Apollo implements GodStrategy {
    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        Tile toTile = board.getTile(x, y);
        // Ensure movement is to an adjacent, orthogonal tile
        int dx = x - worker.getX();
        int dy = y - worker.getY();

        if (!((Math.abs(dx) == 1 && dy == 0) || (Math.abs(dy) == 1 && dx == 0))) {
            return false; // Not a valid Apollo move (not adjacent or same tile)
        }

        // Apollo can swap places with an opponent's worker
        return toTile.isOccupied() ? toTile.getWorker().getPlayer() != worker.getPlayer() : !toTile.hasDome();
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        Tile toTile = board.getTile(x, y);
        Tile fromTile = board.getTile(worker.getX(), worker.getY());

        if (isValidAction(player, worker, board, x, y)) {
            if (toTile.isOccupied() && toTile.getWorker().getPlayer() != player.getPlayerID()) {
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
    public boolean hasPerformedFirstAction() {
        return false; // Not applicable for Apollo
    }

    @Override
    public boolean hasSecondAction() {
        return false; // Not applicable for Apollo
    }

    @Override
    public int hasNum() {
        return 1;
    }

}