package be.kauzas.kernel.utils;

import java.util.List;

/**
 * Build a message based on unformatted string.
 */
public class MessageBuilder implements Builder<String> {

    private String[] strings;

    /**
     * Constructor of {@link MessageBuilder}.
     *
     * @param string unformatted string.
     */
    public MessageBuilder(String string) {
        this(new String[]{string}, true);
    }

    /**
     * Constructor of {@link MessageBuilder}.
     *
     * @param strings unformatted string array.
     */
    public MessageBuilder(String[] strings) {
        this(strings, true);
    }

    /**
     * Constructor of {@link MessageBuilder}.
     *
     * @param strings unformatted string list.
     */
    public MessageBuilder(List<String> strings) {
        this(strings.toArray(String[]::new), true);
    }

    /**
     * Constructor of {@link MessageBuilder}.
     *
     * @param string   unformatted string.
     * @param colorize true if the message should be colored.
     */
    public MessageBuilder(String string, boolean colorize) {
        this(new String[]{string}, colorize);
    }

    /**
     * Constructor of {@link MessageBuilder}.
     *
     * @param strings  unformatted string list.
     * @param colorize true if the message should be colored.
     */
    public MessageBuilder(List<String> strings, boolean colorize) {
        this(strings.toArray(String[]::new), colorize);
    }

    /**
     * Constructor of {@link MessageBuilder}.
     *
     * @param string   unformatted string array.
     * @param colorize true if the message should be colored.
     */
    public MessageBuilder(String[] string, boolean colorize) {
        this.strings = string;
        if (colorize) {
            for (int i = 0; i < strings.length; i++) {
                strings[i] = ChatUtils.color(strings[i]);
            }
        }
    }

    /**
     * Replace the target by the replacement.
     *
     * @param target      target string.
     * @param replacement replacement.
     * @return current builder.
     */
    public MessageBuilder replace(String target, String replacement) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replace(target, replacement);
        }
        return this;
    }

    /**
     * Replace the all target occurrence by the replacement.
     *
     * @param target      target string.
     * @param replacement replacement.
     * @return current builder.
     */
    public MessageBuilder replaceAll(String target, String replacement) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceAll(target, replacement);
        }
        return this;
    }

    /**
     * Replace the first target occurrence by the replacement.
     *
     * @param target      target string.
     * @param replacement replacement.
     * @return current builder.
     */
    public MessageBuilder replaceFirst(String target, String replacement) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].replaceFirst(target, replacement);
        }
        return this;
    }

    /**
     * Get the list of strings.
     *
     * @return List of built string.
     */
    public String[] buildList() {
        return strings;
    }

    /**
     * Get the first built string.
     *
     * @return Built string.
     */
    @Override
    public String build() {
        return strings.length == 0 ? null : strings[0];
    }

}
