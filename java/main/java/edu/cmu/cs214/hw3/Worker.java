public class Worker {
    private Position position;
    private int level;

    public Worker() {
        this.position = new Position(0, 0); // Default position, should be set when the game starts
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void build(Position position) {
        t.build(position);
    }

    public int getLevel() {
        return level;
    }
}
