package be.kauzas.kernel.utils;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

public class StringUtils {

    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String capitalizeItem(String string) {
        String[] parts = string.split("[\\s_]");
        return Arrays.stream(parts).map(StringUtils::capitalize).collect(Collectors.joining(" "));
    }

    public static String formatInt(int number) {
        String input = NumberFormat.getNumberInstance(Locale.FRANCE).format(number);
        return input.replaceAll("\u00a0", ".");
    }

}
