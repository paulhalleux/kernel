package be.kauzas.kernel.cooldown;

import java.util.Calendar;
import java.util.function.BiFunction;

/**
 * Extension of {@link Cooldown} adding a condition to
 * the cooldown validity.
 *
 * @param <T> Object to help validating the cooldown.
 * @param <S> State type of the cooldown.
 */
public class ConditionalCooldown<T, S> extends Cooldown {

    private S state;
    private BiFunction<T, S, Boolean> condition;

    /**
     * Constructor of {@link ConditionalCooldown}.
     *
     * @param end          End of the cooldown.
     * @param initialState Initial state of the cooldown.
     * @param condition    Condition of the cooldown.
     */
    public ConditionalCooldown(Calendar end, S initialState, BiFunction<T, S, Boolean> condition) {
        super(end);
        this.state = initialState;
        this.condition = condition;
    }

    /**
     * Constructor of {@link ConditionalCooldown}.
     *
     * @param condition Condition of the cooldown.
     */
    public ConditionalCooldown(BiFunction<T, S, Boolean> condition) {
        super(null);
        this.condition = condition;
    }

    /**
     * Get current state of the cooldown.
     *
     * @return State of the cooldown.
     */
    public S getState() {
        return state;
    }

    /**
     * Set the end of the cooldown and the new state.
     *
     * @param end   End of the cooldown.
     * @param state New state.
     */
    public void set(Calendar end, S state) {
        this.state = state;
        set(end);
    }

    /**
     * Check if the cooldown is ended or can be bypassed
     * by the condition.
     *
     * @param t Object of the condition.
     * @return true if the cooldown is ended.
     */
    public boolean check(T t) {
        return state == null || (super.verify() || condition.apply(t, state));
    }

}
