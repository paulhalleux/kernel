package be.kauzas.kernel.storage;

import java.util.List;

/**
 * Repository interface for database interactions.
 *
 * @param <K> Type of repository item primary key.
 * @param <T> Type of repository item.
 */
public interface Repository<K, T> {

    /**
     * Find a single element based on his key.
     *
     * @param k Key of the item.
     * @return Found item or null.
     */
    QueryResult<K, T> find(K k);

    /**
     * Find all the elements.
     *
     * @return List of all the elements.
     */
    List<QueryResult<K, T>> findAll();

    /**
     * Find an element based on a column name and the value.
     *
     * @param column Column searched.
     * @param value  Value of column for the searched element.
     * @return Found item or null.
     */
    QueryResult<K, T> findBy(String column, Object value);

    /**
     * Save an element.
     *
     * @param t Element to save.
     * @return Generated key.
     */
    K save(T t);

    /**
     * Remove an element based on his key.
     *
     * @param k Key of the element to remove.
     * @return true if operation succeeded.
     */
    boolean remove(K k);

    /**
     * Update an element based on his key.
     *
     * @param k Key of the element to remove.
     * @param t Item with updated values.
     * @return true if operation succeeded.
     */
    boolean update(K k, T t);

}
