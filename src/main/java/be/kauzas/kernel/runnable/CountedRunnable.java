package be.kauzas.kernel.runnable;

/**
 * Extension of {@link AbstractRunnable} that is executed a certain
 * amount of time.
 */
public abstract class CountedRunnable extends AbstractRunnable {

    private final int count;
    private int currentCount;

    /**
     * Constructor of {@link CountedRunnable}.
     *
     * @param count Amount of execution.
     * @param delay Delay between each execution.
     */
    public CountedRunnable(int count, int delay) {
        this(count, delay, delay);
    }

    /**
     * Constructor of {@link CountedRunnable}.
     *
     * @param count  Amount of execution.
     * @param delay  Time before first execution.
     * @param period Time between each execution.
     */
    public CountedRunnable(int count, int delay, int period) {
        super(delay, period);
        this.count = count;
        this.currentCount = 0;
    }

    /**
     * Run method of the runnable.
     */
    @Override
    public void run() {
        if (currentCount >= count) cancel();
        else execute();
        currentCount++;
    }

    /**
     * Get the current execution
     * count.
     *
     * @return Current execution count.
     */
    public int getCurrentCount() {
        return currentCount;
    }

}
