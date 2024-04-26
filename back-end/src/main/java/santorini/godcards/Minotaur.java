package godcards;

import components.Tile;
import components.Worker;
import interfaces.IBuildStrategy;
import interfaces.IMoveStrategy;
import components.Board;
import components.Player;

import interfaces.GodStrategy;

public class Minotaur implements GodStrategy {
        
    @Override
    public boolean isValidAction(Player player, Worker worker, Board board, int x, int y) {
        Tile toTile = board.getTile(x, y);
        Tile fromTile = board.getTile(worker.getX(), worker.getY());

        int dx = toTile.getX() - fromTile.getX();
        int dy = toTile.getY() - fromTile.getY();
    
        // Check that the movement is exactly one step horizontally or vertically (not diagonal)
        if (!((Math.abs(dx) == 1 && dy == 0) || (Math.abs(dy) == 1 && dx == 0))) {
            return false; // Move is either diagonal, more than one space, or to the same tile
        }
    
        if (toTile.isOccupied() && !toTile.hasDome()) {
            Worker opponent = toTile.getWorker();
            if (opponent.getPlayer() != worker.getPlayer()) {
                // Calculate the tile behind the opponent's worker to check if it is free
                Tile behindTile = board.getTile(toTile.getX() + dx, toTile.getY() + dy);
                // The tile must exist, be unoccupied, and not have a dome
                return behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome();
            }
        }
        return !toTile.isOccupied() && !toTile.hasDome();
    }

    @Override
    public boolean performAction(Player player, Worker worker, Board board, int x, int y) {
        Tile toTile = board.getTile(x, y);
        Tile fromTile = board.getTile(worker.getX(), worker.getY());
    
        if (toTile.isOccupied()) {
            Worker opponent = toTile.getWorker();
            if (opponent.getPlayer() != worker.getPlayer()) {
                int deltaX = x - fromTile.getX();
                int deltaY = y - fromTile.getY();
                Tile behindTile = board.getTile(toTile.getX() + deltaX, toTile.getY() + deltaY);
    
                if (behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome()) {
                    // Push the opponent's worker to the behindTile
                    behindTile.setWorker(opponent);
                    opponent.setPosition(behindTile.getX(), behindTile.getY());
                    toTile.setWorker(null); // Clear the opponent from the current tile
    
                    // Move the Minotaur's worker to the toTile
                    toTile.setWorker(worker);
                    worker.setPosition(x, y);
                    fromTile.setWorker(null);
                    return true;
                }
                return false;
            }
        }
    
        // If no opponent is there, just move the worker normally
        if (!toTile.isOccupied() && board.isValidMove(player, worker, x, y)) {
            toTile.setWorker(worker);
            worker.setPosition(x, y);
            fromTile.setWorker(null);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPerformedFirstAction() {
        return false;
    }

    @Override
    public boolean hasSecondAction() {
        return false;
    }

    @Override
    public boolean hasMove() {
        return true;
    }

    @Override
    public boolean hasBuild() {
        return false;
    }

    @Override
    public boolean hasWin() {
        return false;
    }
    
}

// public class Minotaur implements IMoveStrategy {

//     @Override
//     public boolean isValidMove(Worker worker, Tile fromTile, Tile toTile, Board board) {
//         // Ensure movement is to an adjacent, orthogonal tile
//         int dx = toTile.getX() - fromTile.getX();
//         int dy = toTile.getY() - fromTile.getY();
        
//         // Check that the movement is exactly one step horizontally or vertically (not diagonal)
//         if (!((Math.abs(dx) == 1 && dy == 0) || (Math.abs(dy) == 1 && dx == 0))) {
//             return false; // Move is either diagonal, more than one space, or to the same tile
//         }
    
//         if (toTile.isOccupied() && !toTile.hasDome()) {
//             Worker opponent = toTile.getWorker();
//             if (opponent.getPlayer() != worker.getPlayer()) {
//                 // Calculate the tile behind the opponent's worker to check if it is free
//                 Tile behindTile = board.getTile(toTile.getX() + dx, toTile.getY() + dy);
//                 // The tile must exist, be unoccupied, and not have a dome
//                 return behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome();
//             }
//         }
//         return !toTile.isOccupied() && !toTile.hasDome();
//     }

//     @Override
//     public boolean performMove(Worker worker, int x, int y, Board board) {
//         Tile toTile = board.getTile(x, y);
//         Tile fromTile = board.getTile(worker.getX(), worker.getY());
    
//         if (toTile.isOccupied()) {
//             Worker opponent = toTile.getWorker();
//             if (opponent.getPlayer() != worker.getPlayer()) {
//                 int deltaX = x - fromTile.getX();
//                 int deltaY = y - fromTile.getY();
//                 Tile behindTile = board.getTile(toTile.getX() + deltaX, toTile.getY() + deltaY);
    
//                 if (behindTile != null && !behindTile.isOccupied() && !behindTile.hasDome()) {
//                     // Push the opponent's worker to the behindTile
//                     behindTile.setWorker(opponent);
//                     opponent.setPosition(behindTile.getX(), behindTile.getY());
//                     toTile.setWorker(null); // Clear the opponent from the current tile
    
//                     // Move the Minotaur's worker to the toTile
//                     toTile.setWorker(worker);
//                     worker.setPosition(x, y);
//                     fromTile.setWorker(null);
//                     return true;
//                 }
//                 return false;
//             }
//         }
    
//         // If no opponent is there, just move the worker normally
//         if (!toTile.isOccupied() && isValidMove(worker, fromTile, toTile, board)) {
//             toTile.setWorker(worker);
//             worker.setPosition(x, y);
//             fromTile.setWorker(null);
//             return true;
//         }
//         return false;
//     }

//     @Override
//     public boolean hasPerformedFirstMove() {
//         return false;
//     }
    
//     @Override
//     public boolean hasSecondMove() {
//         return false;
//     }
// }
