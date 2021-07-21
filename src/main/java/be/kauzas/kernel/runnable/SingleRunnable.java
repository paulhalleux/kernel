package be.kauzas.kernel.runnable;

/**
 * Extension of {@link AbstractRunnable} for a single
 * execution runnable.
 */
public abstract class SingleRunnable extends AbstractRunnable {

    /**
     * Constructor of {@link SingleRunnable}.
     *
     * @param delay Delay before the execution.
     */
    public SingleRunnable(int delay) {
        super(delay, -1);
    }

}
