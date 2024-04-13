Starting a Game 


Before starting, move the pom file from s24-hw3 to back-end to make the code run. Once in the root directory (s24-hw3), there are 2 steps:

1. Backend
cd into back-end/ and then run the following 2 commands

mvn install
mvn exec:exec

This will create a port on 8080

2. Frontend
cd into frontend/ and then run the following 2 commands

npm install
npm start

Once these are ran, you can access the Santorini game on localhost:3000


Instructions:

The game is played on a 5 by 5 grid, where each grid can contain towers consisting of blocks and domes. Two players have two workers each on any field of the grid. Throughout the game, the workers move around and build towers. The first worker to make it on top of a level-3 tower wins.

As set up, both players pick starting positions for both their workers on the grid (Player 1 starts). Players take turns. 

In each turn, a Player selects one of their workers, moves this worker to an adjacent, nondiagonal, unoccupied field, and afterward add a block or dome to an unoccupied adjacent nondiagonal field of their new position. Locations with a worker or a dome are considered occupied. Workers can only climb a maximum of one level when moving. Domes can only be built on level-3 towers. You can assume there are infinite tower/dome pieces to play. A player wins if they reach a level-3 tower.

Player 0 begins, placing 2 workers on the Board (Represented as "0"). Then Player 1 places 2 Workers on the Board  (represented as "V"). Then Player 0 begins by choosing one of their players, moving them (game validates it through the backend), checks if they have won and if not, prompts the player to build (game validates it through the backend), then switches turns.