package be.kauzas.kernel.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Class that represent a single or a
 * list of strings that contains placeholders
 * to be replaced.
 */
public class HoldingString {

    private String[] strings;

    /**
     * Constructor of {@link HoldingString} for a single string.
     *
     * @param string String that contains placeholders.
     */
    public HoldingString(String string) {
        this.strings = new String[]{string};
    }

    /**
     * Constructor of {@link HoldingString} for multiple strings.
     *
     * @param strings Strings that contains placeholders.
     */
    public HoldingString(String... strings) {
        this.strings = strings;
    }

    /**
     * Replace an occurrence of the holder in string or
     * strings.
     *
     * @param holder Holder to replace.
     * @param value  Value of the holder.
     * @return Current {@link HoldingString}.
     */
    public HoldingString replace(String holder, Object value) {
        for (int i = 0; i < strings.length; i++) {
            String actualHolder = "%" + holder + "%";
            if (strings[i].contains(actualHolder)) {
                strings[i] = strings[i].replace(actualHolder, value.toString());
                return this;
            }
        }
        return this;
    }

    /**
     * Replace all occurrences of the holder in string or
     * strings.
     *
     * @param holder Holder to replace.
     * @param value  Value of the holder.
     * @return Current {@link HoldingString}.
     */
    public HoldingString replaceAll(String holder, Object value) {
        for (int i = 0; i < strings.length; i++) {
            String actualHolder = "%" + holder + "%";
            strings[i] = strings[i].replaceAll(actualHolder, value.toString());
        }
        return this;
    }

    /**
     * Get the string with replaced holders.
     *
     * @return String with replaced holders.
     */
    public String get() {
        if (strings.length == 0) return "";
        return strings[0];
    }

    /**
     * Get the string list with replaced holders.
     *
     * @return String list with replaced holders as array.
     */
    public String[] asArray() {
        return strings;
    }

    /**
     * Get the string list with replaced holders.
     *
     * @return String list with replaced holders as list.
     */
    public List<String> asList() {
        return Arrays.asList(strings);
    }

}
