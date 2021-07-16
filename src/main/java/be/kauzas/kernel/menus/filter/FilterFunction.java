package be.kauzas.kernel.menus.filter;

import java.util.List;

/**
 * Filter function interface for item
 * filtering.
 *
 * @param <T> Type of items to filter.
 */
@FunctionalInterface
public interface FilterFunction<T> {

    /**
     * Filter the given list of items.
     *
     * @param ts List of items.
     * @return Filtered list of items.
     */
    List<T> filter(List<T> ts);

}
