Determining a Valid Build
For this implementation, we make the build call from the Player class first, which then passes a isValidBuild to the Board class to validate if it is a valid build. Then the Board makes the following verifications:
* First checks if the build call is within valid boundaries
* Checks that the worker is not standing on the block they are attempting to build
* Checks that that block has moved only one block
* Checks there is no worker or a dome on the block they are attempting to build on
If all checks pass, player makes a call to Tile.build to update the nearby Tiles accordingly

Responsibilities:

Player: Controls the Workers and actions, including build, moveWorker, and getWorker
Board: Knows the state of the Board's dimensions, then validates the player's position and if the Tile is occupied or has a dome
Tile: Increases its own level and can set its occupancy

Methods:
Player.build(int x, int y, int workerID)
board.isValidBuild(worker, x, y)
Tile.build(worker, x, y);
Tile.increaseLevel();

Justification:
Information Expert Principle: The Tile object holds the information about itself, so it should be responsible for determining if a build action is valid considering the boundaries of the board as well
Single Responsibility Principle: Tile class is focused on managing its state and the implications of a worker moving to or from it, keeping to the single responsibility principle
Law of Demeter: Making sure other classes do not need to have knowledge and access the Tile fields to update the build

Alternatives Considered

Centralized Building Logic in Game:
Trade-off: Would have reduced the complexity of the Tile class but increased the Game's responsibilities, making it more prone to change for different reasons and violating SRP

Making board build
Considered using the board since it is validating the build if it should realized this violates Law of Demeter since we were making changes and needing to access the Tile class directly, so used the Tile class itself.

Final Design Decision
The chosen design has the Board object act as a coordinator that delegates build actions to the individual Tile objects. Each Tile is then responsible for knowing and updating its state along with Worker to validate it is the right Worker. This approach respects the principles of object-oriented design.

