package carsharing.model.dao;

import java.util.List;

public interface Dao<T> {
    T get(long id);

    List<T> getAll();

    int save(T t);

    int update(long id, T t);

    int delete(long id);

}
