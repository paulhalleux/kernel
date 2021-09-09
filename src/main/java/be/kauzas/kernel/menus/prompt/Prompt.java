package be.kauzas.kernel.menus.prompt;

import org.bukkit.entity.Player;

/**
 * Abstract class representing a prompt.
 *
 * @param <T> Type of the data.
 */
public abstract class Prompt<T> {

    private T value;

    /**
     * Get the entered data.
     *
     * @return Entered data.
     */
    public T getValue() {
        return value;
    }

    /**
     * Set the entered data.
     *
     * @param value Entered data.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Display the prompt to a player.
     *
     * @param player Player to display the prompt.
     */
    public abstract void display(Player player);

}
