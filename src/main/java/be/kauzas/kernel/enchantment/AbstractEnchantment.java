package be.kauzas.kernel.enchantment;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Represent a custom enchantment.
 *
 * @param <T> Event that handle the enchantment.
 */
public abstract class AbstractEnchantment<T extends Event> extends Enchantment implements Listener {

    private final String name;
    private final int maxLevel;

    /**
     * Constructor of {@link AbstractEnchantment}.
     *
     * @param id       Identifier of the enchantment.
     * @param name     Name of the enchantment.
     * @param maxLevel Maximum level of the enchantment.
     */
    public AbstractEnchantment(String id, String name, int maxLevel) {
        super(NamespacedKey.minecraft(id));
        this.name = name;
        this.maxLevel = maxLevel;
    }

    /**
     * Get the name of the enchantment.
     *
     * @return Enchantment name.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the maximum level of the enchantment.
     *
     * @return Maximum level of the enchantment.
     */
    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    /**
     * Check if an enchantment is a treasure enchantment.
     *
     * @return true if the enchantment is a treasure.
     */
    @Override
    public boolean isTreasure() {
        return false;
    }

    /**
     * Check if an enchantment is cursed.
     *
     * @return true if the enchantment is cursed.
     */
    @Override
    public boolean isCursed() {
        return false;
    }

    /**
     * Get the minimum level of the enchantment.
     *
     * @return Minimum level of the enchantment.
     */
    @Override
    public int getStartLevel() {
        return 1;
    }

    /**
     * Handle method of the enchantment.
     * Must be decorated with {@link EventHandler} annotation.
     *
     * @param t Event.
     */
    public abstract void handle(T t);

}
