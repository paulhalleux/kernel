package be.kauzas.kernel.cooldown;

import java.util.Calendar;

/**
 * Represent a cooldown.
 */
public class Cooldown {

    private Calendar end;

    /**
     * Constructor of {@link Cooldown} asking for the end date.
     *
     * @param end End date of the cooldown.
     */
    public Cooldown(Calendar end) {
        this.end = end;
    }

    /**
     * Constructor of {@link Cooldown}.
     */
    public Cooldown() {
        this(null);
    }

    /**
     * Get the end date of the cooldown.
     *
     * @return End of the cooldown.
     */
    public Calendar getEnd() {
        return end;
    }

    /**
     * Define the end date of the cooldown.
     *
     * @param end New cooldown end date.
     */
    public void set(Calendar end) {
        this.end = end;
    }

    /**
     * Check if the cooldown is ended.
     *
     * @return true if the cooldown is ended.
     */
    public boolean verify() {
        return end == null || Calendar.getInstance().getTimeInMillis() >= end.getTimeInMillis();
    }

}
