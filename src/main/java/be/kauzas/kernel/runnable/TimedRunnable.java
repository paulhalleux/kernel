package be.kauzas.kernel.runnable;

/**
 * Extension of {@link AbstractRunnable} for a runnable
 * that last for a certain amount of seconds.
 * <p>
 * The {@link #execute()} method will be executed each second until the end.
 */
public abstract class TimedRunnable extends AbstractRunnable {

    private final int time;
    private int currentTime;

    /**
     * Constructor of {@link TimedRunnable}.
     *
     * @param time Time until the end of the execution. (The {@link #execute()} method
     *             will be executed each second until the end.)
     */
    public TimedRunnable(int time) {
        super(20, 20);
        this.time = time;
    }

    /**
     * Reset the runnable time.
     */
    public void reset() {
        currentTime = 0;
    }

    /**
     * Force the end of the runnable.
     */
    public void end() {
        execute();
        cancel();
    }

    /**
     * Get the current time of the runnable.
     *
     * @return Current time of the runnable.
     */
    public int getCurrentTime() {
        return currentTime;
    }

    /**
     * Run method of the runnable.
     */
    @Override
    public void run() {
        if (currentTime < time) execute();
        else cancel();
    }

}
