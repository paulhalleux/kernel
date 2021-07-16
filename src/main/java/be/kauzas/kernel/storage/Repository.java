package be.kauzas.kernel.storage;

import java.util.List;

public interface Repository<K, T> {

    QueryResult<K, T> find(K k);

    List<QueryResult<K, T>> findAll();

    QueryResult<K, T> findBy(String column, Object value);

    K save(T t);

    boolean remove(K k);

    boolean update(K k, T t);

}
