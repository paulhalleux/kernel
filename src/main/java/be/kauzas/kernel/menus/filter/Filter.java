package be.kauzas.kernel.menus.filter;

import java.util.List;

/**
 * Represent a filter for a certain type of items.
 *
 * @param <T> Type if items to filter.
 */
public class Filter<T> {

    private final String name;
    private final FilterFunction<T> filterFunction;

    /**
     * Constructor of {@link Filter}.
     *
     * @param name           Name of the filter.
     * @param filterFunction Function that apply the filter.
     */
    public Filter(String name, FilterFunction<T> filterFunction) {
        this.name = name;
        this.filterFunction = filterFunction;
    }

    /**
     * Get the name of the filter.
     *
     * @return Name of the filter.
     */
    public String getName() {
        return name;
    }

    /**
     * Filter a list of items based on the filter function.
     *
     * @param base List of items to filter.
     * @return Filtered items.
     */
    public List<T> getFilteredItems(List<T> base) {
        return filterFunction.filter(base);
    }

}
