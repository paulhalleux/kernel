package be.kauzas.kernel.utils;

/**
 * Interface for Lang enum.
 */
public interface LangProvider {

    /**
     * Get the string values.
     *
     * @return Strings.
     */
    String[] get();

    /**
     * Get the string values and replace
     * placeholders with given values.
     *
     * @param placeholders Placeholders to replace.
     * @return Strings.
     */
    String[] get(String[]... placeholders);

    /**
     * Get an {@link HoldingString} from the base string.
     *
     * @return HoldingString for base string.
     */
    HoldingString getHolding();

    /**
     * Get the first string.
     *
     * @return First string.
     */
    default String first() {
        return get()[0];
    }

}
