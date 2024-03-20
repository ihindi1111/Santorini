# Justification for handling state
Below, describe where you stored each of the following states and justify your answers with design principles/goals/heuristics/patterns. Discuss the alternatives and trade-offs you considered during your design process.

## Players
Stored in a list within the Game class.

Justification: Following the principle of encapsulation, The Game class is the central area to get to the other classes and direct connector with players. This aligns with the Single Responsibility Principle since the Game class is responsible for managing the game's overall state, following 

Alternatives Considered: Storing players in each Worker object or distributing them across other classes but this would cause increased complexity and less cohesion

## Current player
Stored as a reference within the Game class.

Justification: Storing the current player in the Game class supports the principle of least knowledge, since it is small to track the turn and makes it easto update and switch players.

Alternatives Considered: Storing the current state of each player in their respective group but this would increase code duplication in the main code

## Worker locations
Stored within each Worker object

Justification: This adheres to the principle of information expert since the Worker is its own expert on its location. It allows for easy updates to positions and simplifies checking for legal moves/builds.

Alternatives Considered: Storing all worker locations directly in the Board class. This was deemed less optimal for encapsulation and would complicate the relationship between workers and their positions.

## Towers
Stored in the Tile objects managed by the Board class.

Justification: Each Tile being responsible for its own state follows the information expert principle. The Board class, as a collection of Tiles, naturally fits the role of managing the grid of tiles and their states and makes it easy to track if a tile is available or not, creating a more cohesive and less dependencies on other classes.

Alternatives Considered: Storing tower states in a separate class but this causes higher coupling, increasing dependency and less cohesion

## Winner
Stored in the Game class.

Justification: The Game class is responsible for managing the flow of the game, making it the best choice for tracking the winner of the game. This centralizes game control and outcomes within a single class, adhering to Single Responsibility Principle.

Alternatives Considered: Storing the winner state in a Player object or external state manager. This could complicate accessing the winner's information post-game

## Design goals/principles/heuristics considered
Single Responsibility Principle: Each class should have a single responsibility.
Information Expert Principle: Keeping data and the methods that manipulate that data together.
Principle of Least Knowledge (Law of Demeter): An object should not expose details of its internal components/avoid accessing the internal data and methods of other objects.
Information Expert: Assign responsibility to the class that has the most information required to fulfill it.
Locality: Keep related data and behaviors in the same class as much as possible.
Low coupling: Keeping classes independent and limiting direct knowledge of other classes

## Alternatives considered and analysis of trade-offs
During the design process,the alternatives we considered included distributing methods and creating more states evenly among classes. The primary trade-offs involved balancing complexity, cohesion, and coupling. Storing state in a centralized manner, (one example is the current player and if there is a winner tracked in the Game class), simplifies interactions but places more responsibility on a single class. Distributing state (e.g., worker locations within Worker objects and tower states within Tile objects) supports low coupling and adheres to the information expert principle.

The chosen design seems to best satisfy the design goals of maintaining clarity and easy maintenance while ensuring the system remains extensible for potential future improvements.