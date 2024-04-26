// package godcards;

// import components.Board;
// import components.Tile;
// import components.Worker;


// public class Artemis implements IMoveStrategy {
//     private Tile initialTile = null; // To track the initial position of the move

//     @Override
//     public boolean isValidMove(Player player, Worker worker, Tile fromTile, Tile toTile, Board board) {
//         if (initialTile == null) {
//             initialTile = fromTile; // Store the initial tile on the first move
//         } else if (toTile == initialTile) {
//             return false; // Prevent moving back to the initial tile on the second move
//         }

//         // Standard move validity: ensure the move is to an adjacent tile, not occupied by a dome
//         return board.isValidMove(player, worker, fromTile, toTile);
//     }

//     @Override
//     public boolean performMove(Worker worker, int x, int y, Board board) {
//         Tile fromTile = board.getTile(worker.getX(), worker.getY());
//         Tile toTile = board.getTile(x, y);

//         if (isValidMove(worker, fromTile, toTile, board)) {
//             worker.setPosition(x, y); // Move worker to new position
//             fromTile.setWorker(null); // Clear the worker from the previous tile
//             toTile.setWorker(worker); // Set worker to new tile

//             // If this is the second move, reset initialTile for future moves
//             if (initialTile != null && fromTile != initialTile) {
//                 initialTile = null;
//             }
//             return true;
//         }
//         return false;
//     }

//     @Override
//     public boolean hasPerformedFirstMove() {
//         return initialTile != null; // Returns true if the initial move has been made
//     }

//     @Override
//     public boolean hasSecondMove() {
//         return initialTile != null; // Allows a second move if the first move has been made
//     }
// }
