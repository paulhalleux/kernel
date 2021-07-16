package be.kauzas.kernel.menus.filter;

import java.util.List;

public class Filter<T> {

    private final String name;
    private final FilterFunction<T> filterFunction;

    public Filter(String name, FilterFunction<T> filterFunction) {
        this.name = name;
        this.filterFunction = filterFunction;
    }

    public String getName() {
        return name;
    }

    public List<T> getFilteredItems(List<T> base) {
        return filterFunction.filter(base);
    }

}
