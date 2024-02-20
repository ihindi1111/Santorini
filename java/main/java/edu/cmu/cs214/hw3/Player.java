public class Player {
    private Worker[] workers;

    /**
     * Initalizes a new Player
     */
    public Player() {
        workers = new Worker[]{new Worker(), new Worker()};
    }

    /**
     * Retrieves worker 1 or 2
     * @param workerIndex
     * @return
     */
    public Worker getWorker(int workerIndex) throws IllegalStateException{
        if (workerIndex == 0 || workerIndex == 1) return workers[workerIndex];
        else {
            throw new IllegalStateException("Cannot build beyond the maximum level.");
        }
    }
}