package be.kauzas.kernel.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Utility object that retrieve and set tags
 * for a given item meta.
 */
public class ItemTags {

    private final ItemMeta itemMeta;
    private final JavaPlugin plugin;

    /**
     * Constructor of {@link ItemTags}.
     *
     * @param itemMeta Item meta of the item.
     * @param plugin   Plugin used to store data.
     */
    public ItemTags(ItemMeta itemMeta, JavaPlugin plugin) {
        this.itemMeta = itemMeta;
        this.plugin = plugin;
    }

    /**
     * Get a stored data as string.
     *
     * @param key Key of the stored data.
     * @return Stored data or null if not found.
     */
    public String getString(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    /**
     * Add a custom string tag to the item.
     *
     * @param key   Key of the stored data.
     * @param value Value to store.
     */
    public void setString(String key, String value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
    }

    /**
     * Get a stored data as integer.
     *
     * @param key Key of the stored data.
     * @return Stored data or null if not found.
     */
    public Integer getInt(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
    }

    /**
     * Add a custom integer tag to the item.
     *
     * @param key   Key of the stored data.
     * @param value Value to store.
     */
    public void setInt(String key, int value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value);
    }

    /**
     * Get a stored data as boolean.
     *
     * @param key Key of the stored data.
     * @return Stored data or null if not found.
     */
    public boolean getBoolean(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, -1) == 1;
    }

    /**
     * Add a custom boolean tag to the item.
     *
     * @param key   Key of the stored data.
     * @param value Value to store.
     */
    public void setBoolean(String key, boolean value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value ? 1 : -1);
    }

    /**
     * Get a stored data as float.
     *
     * @param key Key of the stored data.
     * @return Stored data or null if not found.
     */
    public Float getFloat(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.FLOAT);
    }

    /**
     * Add a custom float tag to the item.
     *
     * @param key   Key of the stored data.
     * @param value Value to store.
     */
    public void setFloat(String key, float value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.FLOAT, value);
    }

    /**
     * Get a stored data as double.
     *
     * @param key Key of the stored data.
     * @return Stored data or null if not found.
     */
    public Double getDouble(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE);
    }

    /**
     * Add a custom double tag to the item.
     *
     * @param key   Key of the stored data.
     * @param value Value to store.
     */
    public void setDouble(String key, double value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE, value);
    }

}
