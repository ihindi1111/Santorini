package framework.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import framework.core.GameFrameworkImpl;

public class GameState {

    private final String name;
    private final String footer;
    private final Cell[] cells;
    private final Plugin[] plugins;
    private final String numColStyle;
    private final String currentPlayer;
    private final String gameOverMsg;
    private boolean isGodCardSelectionActive;

    private GameState(String name, String footer, Cell[] cells, Plugin[] plugins, String numColStyle, String currentPlayer, String gameOverMsg) {
        this.name = name;
        this.footer = footer;
        this.cells = cells;
        this.plugins = plugins;
        this.numColStyle = numColStyle;
        this.currentPlayer = currentPlayer;
        this.gameOverMsg = gameOverMsg;
        this.isGodCardSelectionActive = false;
    }

    public static GameState forGame(GameFrameworkImpl game) {
        String name = game.getGameName();
        String footer = game.getFooter();
        Cell[] cells = getCells(game);
        Plugin[] plugins = getPlugins(game);
        String numColStyle = getNumColStyle(game);
        String currentPlayer = game.getCurrentPlayerName();
        String gameOverMsg = game.getGameOverMsg();
        return new GameState(name,footer,cells,plugins,numColStyle, currentPlayer, gameOverMsg);
    }

    private static String getNumColStyle(GameFrameworkImpl game) {
        int numCols = game.getGridWidth();
        List<String> style = new ArrayList<String>();
	String width = String.format("%.1f%%", 100d/numCols);
        for (int i=0; i<numCols; i++){
            style.add(width);
        }
        return String.join(" ", style);

    }

    private static Plugin[] getPlugins(GameFrameworkImpl game) {
        List<String> gamePlugins = game.getRegisteredPluginName();
        Plugin[] plugins = new Plugin[gamePlugins.size()];
        for (int i=0; i<gamePlugins.size(); i++){
            plugins[i] = new Plugin(gamePlugins.get(i));
        }
        return plugins;
    }

    public void updateCellStates(GameFrameworkImpl game) {
        if (isGodCardSelectionActive) {
            int index = 0;
            for (String key : godCards.buildStrategies.keySet()) {
                if (index >= 25) break;  // Prevent exceeding the grid size
                cells[index++] = new Cell(key, true, "Build");
            }
            // Adding move strategies to the selection board
            for (String key : godCards.moveStrategies.keySet()) {
                if (index >= 25) break;
                cells[index++] = new Cell(key, true, "Move");
            }
            // Adding win strategies to the selection board
            for (String key : godCards.winStrategies.keySet()) {
                if (index >= 25) break;
                cells[index++] = new Cell(key, true, "Win");
            }
            // Fill remaining cells with empty cells if any
            while (index < 25) {
                cells[index++] = new Cell("", false, "");
            }
        }
        else {
            for (int y = 0; y < game.getGridHeight(); y++) {
                for (int x = 0; x < game.getGridWidth(); x++) {
                    // Calculate the index for the linear cells array based on x, y coordinates
                    int index = y * game.getGridWidth() + x;
                    
                    // Fetch the current text for the cell from the game logic
                    // This might be a player symbol, a tower height, or any other game-specific representation
                    String newText = game.getSquare(x, y);
                    
                    // Determine if the cell is playable based on the current game logic
                    boolean isPlayable = game.isSquarePlayable(x, y);
                    
                    // Update the cell's state
                    cells[index].setText(newText); // Update text to reflect current state
                    cells[index].setPlayable(isPlayable); // Update playability
                }
            }
        }
        // Optionally, if your architecture supports it, trigger a UI refresh here to reflect the updates
    }


    private static Cell[] getCells(GameFrameworkImpl game) {
        int height = game.getGridHeight();
        int width = game.getGridWidth();
        Cell[] cells = new Cell[height*width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                String text = game.getSquare(x,y);
                boolean playable  = game.isSquarePlayable(x,y);
                cells[width * y + x] = new Cell(x, y, text, playable);
            }
        }
        return cells;
    }

    // public void updateCellStates(GameFrameworkImpl game) {
    //     for (int x = 0; x < game.getGridWidth(); x++) {
    //         for (int y = 0; y < game.getGridHeight(); y++) {
    //             int index = game.getGridWidth() * y + x;
    //             // Update existing cells instead of creating new ones
    //             this.cells[index].setText(game.getSquare(x, y));
    //             this.cells[index].setPlayable(game.isSquarePlayable(x, y));
    //         }
    //     }
    // }

    public void setGodCardSelectionActive(boolean godCardSelectionActive) {
        isGodCardSelectionActive = godCardSelectionActive;
    }

    public Cell[] getCells() {
        return this.cells;
    }

    public String getName() {
        return name;
    }

    public String getFooter() {
        return footer;
    }

    public Plugin[] getPlugins() {
        return plugins;
    }

    public String getNumColStyle() {
        return numColStyle;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getGameOverMsg() {
        return gameOverMsg;
    }

    @Override
    public String toString() {
        return ("{ \"name\": \"" + this.name + "\"," +
                " \"footer\": \"" + this.footer + "\"," +
                " \"cells\": " +  Arrays.toString(cells) + "," +
                " \"plugins\": " +  Arrays.toString(plugins) + "," +
                " \"numColStyle\": \"" + this.numColStyle + "\"," +
                " \"currentPlayer\": \"" + this.currentPlayer + "\"," +
                " \"gameOverMsg\": \"" + this.gameOverMsg + "\"}").replace("null", "");

    }
}

