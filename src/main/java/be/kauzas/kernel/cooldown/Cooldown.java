package be.kauzas.kernel.cooldown;

import java.util.Calendar;

public class Cooldown {

    private Calendar end;

    public Cooldown(Calendar end) {
        this.end = end;
    }

    public Cooldown() {
        this(null);
    }

    public Calendar getEnd() {
        return end;
    }

    public void set(Calendar end) {
        this.end = end;
    }

    public boolean verify() {
        return end == null || Calendar.getInstance().getTimeInMillis() >= end.getTimeInMillis();
    }

}
