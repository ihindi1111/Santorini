package plugin;

import games.RockPaperScissors;
import framework.core.GameFrameworkImpl;
import framework.core.GameFramework;
import framework.core.GamePlugin;

/**
 * An example Rock Paper Scissors game plug-in.
 */
public class RpsPlugin implements GamePlugin<String> {

    private static final String GAME_NAME = "Rocks Paper Scissors";

    private static final int WIDTH = 3;
    private static final int HEIGHT = 1;

    private static final String PLAYER_WON_MSG = "You won!";
    private static final String COMPUTER_WON_MSG = "The computer won!";
    private static final String GAME_TIED_MSG = "The game ended in a tie.";

    private static final String GAME_START_FOOTER = "You are playing Rocks Paper Scissors with a computer!";

    private GameFramework framework;
    private RockPaperScissors.Result gameResult = null;

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public int getGridWidth() {
        return WIDTH;
    }

    @Override
    public int getGridHeight() {
        return HEIGHT;
    }

    @Override
    public void onRegister(GameFramework f) {
        framework = f;
    }

    @Override
    public void onNewGame() {
        framework.setFooterText(GAME_START_FOOTER);
        framework.setSquare(RockPaperScissors.ROCK.ordinal(), 0, "Rock");
        framework.setSquare(RockPaperScissors.PAPER.ordinal(), 0, "Paper");
        framework.setSquare(RockPaperScissors.SCISSORS.ordinal(), 0, "Scissors");
        gameResult = null;
    }

    @Override
    public void onNewMove() { } // Nothing to do here.

    @Override
    public boolean isMoveValid(int x, int y) {
        return true; // Impossible to make an invalid move.
    }

    @Override
    public boolean isMoveOver() {
        return gameResult != null;
    }

    @Override
    public void onMovePlayed(int x, int y) {
        gameResult = RockPaperScissors.play(RockPaperScissors.fromOrdinal(x));
    }

    @Override
    public boolean isGameOver() {
        return gameResult != null;
    }

    @Override
    public String getGameOverMessage() {
        switch(gameResult) {
            case TIE: return GAME_TIED_MSG;
            case WIN: return PLAYER_WON_MSG;
            case LOSE: return COMPUTER_WON_MSG;
            default: throw new IllegalStateException("Called getGameOverMessage with incomplete game");
        }
    }

    @Override
    public void onGameClosed() { } // Nothing to do here.

    @Override
    public String currentPlayer() {
        return "Human";
    }
}
