package be.kauzas.kernel.utils;

import org.bukkit.ChatColor;

public class ChatUtils {

    public static String color(String base) {
        return ChatColor.translateAlternateColorCodes('&', base);
    }

}
