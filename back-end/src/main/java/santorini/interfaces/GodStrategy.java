package interfaces;

import components.Board;
import components.Tile;
import components.Worker;
import components.Player;

public interface GodStrategy {
    boolean isValidAction(Player player, Worker worker, Board board, int x, int y);
    boolean performAction(Player player, Worker worker, Board board, int x, int y);
    boolean hasPerformedFirstAction();
    boolean hasSecondAction();
    int hasNum();
}
