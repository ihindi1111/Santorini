Determining a Valid Build

When validating a build action, the process initiates from the Player class attempting a build with a valid selectedPlayer initially, and within the game class calls isValidBuild on the Board class. The Board class then checks:

* The build action is within valid boundaries, including nearby, no worker is on the block, the player is the correct one.
* The worker isn’t attempting to build on its own tile.
* The target tile for building is adjacent to the worker’s current position.
* The tile is free from other workers and domes.
* There is no dome
* 
* For a player with the Demeter card, an additional validation ensures the player can perform a second build on a different adjacent tile, except the one just built upon, or allows them to click on themselves to skip, enhancing strategic depth without violating game balance

Responsibilities:

Player: Holds workers
Board: Knows the state of the Board's dimensions, then validates the player's position and if the Tile is occupied or has a dome. Maintains the game board’s state, validates build positions relative to the worker’s position, checks Tile occupancy, if it is the correct player choosing it, valid height distance, whether the block already has a dome, whether the block is in bounds, and worker is not staying on the same square. We call on the Tiles to check dome, occupation, and level
Tile: Manages its level and occupancy state, performs actual building.
Methods:

Santorini.build(Worker worker, int x, int y)
Board.isValidBuild(Player player, Worker worker, int x, int y)
Tile.build()
Justification:

Information Expert Principle: The Tile object, which directly manages its state, determines the validity of a build action.
Single Responsibility Principle (SRP): By keeping the Tile class focused on its state management, we adhere to SRP, avoiding overloading other classes with multiple responsibilities.
Law of Demeter: This design minimizes direct interactions with the Tile properties by other classes, promoting loose coupling.

Alternatives Considered:

Centralizing Building Logic in Game: This approach was rejected to avoid overburdening the Game class with direct tile management tasks, which would conflict with SRP and overburden Game responsbilities
Board Managing Builds: Initially considered, this approach would have violated the Law of Demeter by requiring the Board to manipulate Tile details directly.
Final Design Decision:
The finalized design delegates the coordination of build actions to the Board, which then allows individual Tiles to update their own state. This setup not only supports extensibility for implementing additional features like god cards but also maintains clean separation of concerns within the game’s architecture.
