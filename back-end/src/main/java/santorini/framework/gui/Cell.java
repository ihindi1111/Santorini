package framework.gui;

public class Cell {
    private final int x;
    private final int y;
    private String text;
    private boolean playable;

    Cell(int x, int y, String text, boolean playable) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.playable = playable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPlayable() {
        return this.playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    @Override
    public String toString() {
        return "{ \"text\": \"" + this.text + "\"," +
                " \"playable\": " + this.playable + "," +
                " \"x\": " + this.x + "," +
                " \"y\": " + this.y + " }" ;
    }
}
