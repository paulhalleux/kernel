package be.kauzas.kernel.runnable;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Extension to {@link BukkitRunnable} to make the usage of Runnable
 * easier.
 */
public abstract class AbstractRunnable extends BukkitRunnable {

    private final int delay;
    private final int period;

    /**
     * Constructor of {@link AbstractRunnable}.
     *
     * @param delay  Delay before the first execution.
     * @param period Period between two execution.
     */
    public AbstractRunnable(int delay, int period) {
        this.delay = delay;
        this.period = period;
    }

    /**
     * Get the delay before the first execution.
     *
     * @return Delay before first execution.
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Get the period between each execution.
     *
     * @return Delay between executions.
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Run method of the runnable.
     */
    @Override
    public void run() {
        execute();
    }

    /**
     * Start method of the runnable.
     *
     * @param plugin Plugin to start the runnable in.
     */
    public void start(Plugin plugin) {
        if (period == -1) runTaskLater(plugin, delay);
        else runTaskTimer(plugin, delay, period);
    }

    /**
     * Code to be executed by the runnable.
     */
    public abstract void execute();

}
