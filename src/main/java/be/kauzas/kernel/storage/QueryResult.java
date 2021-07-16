package be.kauzas.kernel.storage;

public class QueryResult<K, T> {

    private final K key;
    private final T value;

    public QueryResult(K key, T value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

}
