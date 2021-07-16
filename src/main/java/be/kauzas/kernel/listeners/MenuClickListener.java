package be.kauzas.kernel.listeners;

import be.kauzas.kernel.KernelPlugin;
import be.kauzas.kernel.events.menus.MenuBackEvent;
import be.kauzas.kernel.events.menus.MenuClickEvent;
import be.kauzas.kernel.events.menus.MenuPageChangedEvent;
import be.kauzas.kernel.menus.AbstractMenu;
import be.kauzas.kernel.menus.MenuHolder;
import be.kauzas.kernel.menus.pagination.PaginatedMenu;
import be.kauzas.kernel.menus.pagination.PaginationItem;
import be.kauzas.kernel.options.Backable;
import be.kauzas.kernel.service.Service;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Listener of the inventory click event that
 * handle click in registered menus given in the
 * constructor.
 */
public class MenuClickListener implements Listener {

    private final List<AbstractMenu> menus;

    /**
     * Constructor of {@link MenuClickListener} asking for
     * an instance of {@link Service} that register {@link AbstractMenu}.
     *
     * @param plugin {@link KernelPlugin} implementation.
     */
    public MenuClickListener(KernelPlugin plugin) {
        this.menus = plugin.getMenuService().getItems();
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {

        Inventory clicked = event.getClickedInventory();
        ItemStack current = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();

        if (clicked == null || current == null || current.getType() == Material.AIR || !(clicked.getHolder() instanceof MenuHolder))
            return;

        MenuHolder identifier = (MenuHolder) clicked.getHolder();
        menus.stream().filter(menu -> identifier.getUniqueId().equals(menu.getUniqueId()))
                .forEach(menu -> {
                    identifier.getMenu().onClick(event);
                    handleBackable(player, event.getSlot(), identifier.getMenu());
                    handlePagination(player, event.getSlot(), identifier.getMenu());

                    Bukkit.getPluginManager().callEvent(new MenuClickEvent(player, event.getSlot(), current, menu, event));

                });
    }

    /**
     * Handle menu back button if menu implements
     * Backable interface.
     *
     * @param player Player who clicked.
     * @param slot   Slot player clicked at.
     * @param menu   Menu player clicked on.
     */
    private void handleBackable(Player player, int slot, AbstractMenu menu) {
        if (menu instanceof Backable) {
            Backable backable = (Backable) menu;
            if (slot == backable.getBackSlot()) {
                // Call MenuBackEvent event
                MenuBackEvent event = new MenuBackEvent(backable, player);
                Bukkit.getPluginManager().callEvent(event);

                if (!event.isCancelled())
                    // Open destination menu
                    backable.getBackDestination().display(player);
            }
        }
    }

    /**
     * Handle menu pagination if menu instanceof
     * PaginatedMenu
     *
     * @param player Player who clicked.
     * @param slot   Slot player clicked at.
     * @param menu   Menu player clicked on.
     */
    private void handlePagination(Player player, int slot, AbstractMenu menu) {
        if (menu instanceof PaginatedMenu) {
            PaginatedMenu paginatedMenu = (PaginatedMenu) menu;
            PaginationItem previous = paginatedMenu.getPagination().getPreviousItem(paginatedMenu);
            PaginationItem next = paginatedMenu.getPagination().getNextItem(paginatedMenu);
            if (slot == previous.getSlot() && paginatedMenu.getCurrentPage() > 0) {
                paginatedMenu.setCurrentPage(paginatedMenu.getCurrentPage() - 1);
                paginatedMenu.update(player);
                Bukkit.getPluginManager().callEvent(new MenuPageChangedEvent(player, paginatedMenu, paginatedMenu.getCurrentPage() + 1, paginatedMenu.getCurrentPage()));
            } else if (slot == next.getSlot() && paginatedMenu.getCurrentPage() < (paginatedMenu.getPageAmount() - 1)) {
                paginatedMenu.setCurrentPage(paginatedMenu.getCurrentPage() + 1);
                paginatedMenu.update(player);
                Bukkit.getPluginManager().callEvent(new MenuPageChangedEvent(player, paginatedMenu, paginatedMenu.getCurrentPage() - 1, paginatedMenu.getCurrentPage()));
            }
        }
    }

}
