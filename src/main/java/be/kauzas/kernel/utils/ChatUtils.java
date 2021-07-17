package be.kauzas.kernel.utils;

import org.bukkit.ChatColor;

/**
 * Utility class that contains useful
 * methods for chat.
 */
public class ChatUtils {

    /**
     * Translate color codes of the given string.
     *
     * @param base Uncolored string.
     * @return Colored string.
     */
    public static String color(String base) {
        return ChatColor.translateAlternateColorCodes('&', base);
    }

}
