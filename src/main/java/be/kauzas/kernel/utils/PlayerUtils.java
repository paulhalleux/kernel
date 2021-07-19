package be.kauzas.kernel.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PlayerUtils {

    public static void give(Player player, ItemStack itemStack) {
        HashMap<Integer, ItemStack> nope = player.getInventory().addItem(itemStack);
    }

    public static void drop(Player player, ItemStack itemStack) {
        player.getWorld().dropItemNaturally(player.getLocation(), itemStack);
    }

    public static void giveOrDrop(Player player, ItemStack itemStack) {
        ItemStack item = itemStack.clone();
        HashMap<Integer, ItemStack> nope = player.getInventory().addItem(item);
        for (Map.Entry<Integer, ItemStack> entry : nope.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), entry.getValue());
        }
        player.updateInventory();
    }

    public static void giveOrDrop(Player player, ItemStack itemStack, int amount) {
        ItemStack item = itemStack.clone();

        int rest = amount % 64;
        int stacks = amount / 64;
        item.setAmount(64);
        for (int i = 0; i < stacks; i++) {
            giveOrDrop(player, item);
        }
        if (rest > 0) {
            item.setAmount(rest);
            giveOrDrop(player, item);
        }
    }

}
