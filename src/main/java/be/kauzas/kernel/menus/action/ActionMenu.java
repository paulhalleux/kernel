package be.kauzas.kernel.menus.action;

import be.kauzas.kernel.menus.AbstractMenu;
import be.kauzas.kernel.service.IgnoreService;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

/**
 * {@link AbstractMenu} extension that is based on {@link ActionItem} object.
 * The inventory is automatically filled with the array of {@link ActionItem} given
 * in constructor and {@link ActionMenu#onClick(InventoryClickEvent)} is connected
 * to the actions of the action items.
 * <p>
 * You can still add items manually by overriding {@link ActionMenu#setContent(Player, Inventory)} method
 * and you can add custom event handling by overriding {@link ActionMenu#onClick(InventoryClickEvent)} method.
 */
@IgnoreService
public abstract class ActionMenu extends AbstractMenu {

    private final AbstractActionItem[] items;

    /**
     * Constructor of {@link ActionMenu}.
     *
     * @param uniqueId      Inventory unique identifier used to recognise the inventory in
     *                      the inventory click event.
     * @param title         Shown title of the inventory.
     * @param inventoryType Type type of the inventory from {@link InventoryType} enum.
     * @param items         List of {@link AbstractActionItem} to display in the inventory.
     */
    public ActionMenu(String uniqueId, String title, InventoryType inventoryType, AbstractActionItem[] items) {
        super(uniqueId, title, inventoryType);
        this.items = items;
    }

    /**
     * Constructor of {@link ActionMenu}.
     *
     * @param uniqueId Inventory unique identifier used to recognise the inventory in
     *                 the inventory click event.
     * @param title    Shown title of the inventory.
     * @param size     Slot amount of the inventory.
     * @param items    List of {@link AbstractActionItem} to display in the inventory.
     */
    public ActionMenu(String uniqueId, String title, int size, AbstractActionItem[] items) {
        super(uniqueId, title, size);
        this.items = items;
    }

    /**
     * Fill inventory with the {@link AbstractActionItem} list.
     *
     * @param player    Player for whom we open the inventory.
     * @param inventory The inventory that will be opened.
     */
    @Override
    public void setContent(Player player, Inventory inventory) {
        for (AbstractActionItem item : items) {
            inventory.setItem(item.getSlot(), item.getItemStack());
        }
    }

    /**
     * Bind the inventory click event to action of the
     * {@link AbstractActionItem} list.
     *
     * @param event Concerned {@link InventoryClickEvent}.
     */
    @Override
    public void onClick(InventoryClickEvent event) {
        for (AbstractActionItem item : items) {
            if (event.getSlot() == item.getSlot())
                item.getAction().execute(this, event);
        }
    }

}
