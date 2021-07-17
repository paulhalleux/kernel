package be.kauzas.kernel.items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemTags {

    private final ItemMeta itemMeta;
    private final JavaPlugin plugin;

    public ItemTags(ItemMeta itemMeta, JavaPlugin plugin) {
        this.itemMeta = itemMeta;
        this.plugin = plugin;
    }

    public String getString(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.STRING);
    }

    public void setString(String key, String value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.STRING, value);
    }

    public Integer getInt(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.INTEGER);
    }

    public void setInt(String key, int value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value);
    }

    public boolean getBoolean(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().getOrDefault(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, -1) == 1;
    }

    public void setBoolean(String key, boolean value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.INTEGER, value ? 1 : -1);
    }

    public Float getFloat(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.FLOAT);
    }

    public void setFloat(String key, float value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.FLOAT, value);
    }

    public Double getDouble(String key) {
        assert itemMeta != null;
        return itemMeta.getPersistentDataContainer().get(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE);
    }

    public void setDouble(String key, double value) {
        assert itemMeta != null;
        itemMeta.getPersistentDataContainer().set(new NamespacedKey(plugin, key), PersistentDataType.DOUBLE, value);
    }

}
