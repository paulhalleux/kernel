package be.kauzas.kernel.utils;

/**
 * Interface of a builder.
 *
 * @param <T> Type of built object.
 */
public interface Builder<T> {

    /**
     * Build a {@code T} object.
     *
     * @return Built object.
     */
    T build();

}
