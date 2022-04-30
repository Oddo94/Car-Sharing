package carsharing.model.dao;

import java.util.List;

public interface Dao<T> {
    T get(long id);

    List<T> getAll();

    void save(T t);

    void update(long id, T t);

    void delete(long id);

}
