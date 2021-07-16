package be.kauzas.kernel.cooldown;

import java.util.Calendar;
import java.util.function.BiFunction;

public class ConditionalCooldown<T, S> extends Cooldown {

    private S state;
    private BiFunction<T, S, Boolean> condition;

    public ConditionalCooldown(Calendar end, S initialState, BiFunction<T, S, Boolean> condition) {
        super(end);
        this.state = initialState;
        this.condition = condition;
    }

    public ConditionalCooldown(BiFunction<T, S, Boolean> condition) {
        super(null);
        this.condition = condition;
    }

    public S getState() {
        return state;
    }

    public void set(Calendar end, S previousState) {
        this.state = previousState;
        set(end);
    }

    public boolean check(T t) {
        return state == null || (super.verify() || condition.apply(t, state));
    }

}
