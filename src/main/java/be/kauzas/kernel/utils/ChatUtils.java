package be.kauzas.kernel.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class that contains useful
 * methods for chat.
 */
public class ChatUtils {

    private static final Pattern HEX_PATTERN = Pattern.compile("<#(\\w{5}[0-9a-f])>");

    /**
     * Translate color codes of the given string.
     *
     * @param base Uncolored string.
     * @return Colored string.
     */
    public static String color(String base) {
        return ChatColor.translateAlternateColorCodes('&', hex(base));
    }

    /**
     * Translate color codes of the given string with hex support.
     *
     * @param base Uncolored string.
     * @return Colored string.
     */
    public static String hex(String base) {

        Matcher matcher = HEX_PATTERN.matcher(base);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, net.md_5.bungee.api.ChatColor.of("#" + matcher.group(1)).toString());
        }

        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());

    }

}
