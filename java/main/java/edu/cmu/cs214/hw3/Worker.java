public class Player {
    private Worker[] workers;

    public Player() {
        workers = new Worker[]{new Worker(), new Worker()};
    }

    public Worker getWorker(int workerIndex) {
        if (workerIndex == 0 || workerIndex == 1) return workers[workerIndex];
    }
}
