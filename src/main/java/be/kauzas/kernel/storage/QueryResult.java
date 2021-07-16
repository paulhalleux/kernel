package be.kauzas.kernel.storage;

/**
 * Represent the result of a database query.
 *
 * @param <K> Primary Key type of the fetched item.
 * @param <T> Type of the fetched item.
 */
public class QueryResult<K, T> {

    private final K key;
    private final T value;

    /**
     * Constructor of {@link QueryResult}.
     *
     * @param key   Key of the item.
     * @param value Value if the result.
     */
    public QueryResult(K key, T value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Get the key of the fetched item.
     *
     * @return Key of the item.
     */
    public K getKey() {
        return key;
    }

    /**
     * Get the fetched value.
     *
     * @return Fetched value.
     */
    public T getValue() {
        return value;
    }

}
