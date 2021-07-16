package be.kauzas.kernel.menus.filter;

import java.util.List;

@FunctionalInterface
public interface FilterFunction<T> {

    List<T> filter(List<T> ts);

}
