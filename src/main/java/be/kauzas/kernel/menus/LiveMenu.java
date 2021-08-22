package be.kauzas.kernel.menus;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class LiveMenu extends AbstractMenu {

    private final int delay;
    private final Plugin plugin;
    private BukkitRunnable runnable;

    public LiveMenu(String uniqueId, String title, InventoryType inventoryType, Plugin plugin, int delay) {
        super(uniqueId, title, inventoryType);
        this.delay = delay;
        this.plugin = plugin;
    }

    public LiveMenu(String uniqueId, String title, int size, Plugin plugin, int delay) {
        super(uniqueId, title, size);
        this.delay = delay;
        this.plugin = plugin;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public void update(Player player) {
        super.update(player);
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                onUpdate(player, getInventory());
            }
        };
        runnable.runTaskTimer(plugin, delay, delay);
    }

    public abstract void onUpdate(Player player, Inventory inventory);

}
