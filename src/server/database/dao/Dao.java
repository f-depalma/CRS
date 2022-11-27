package server.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface Dao<E> {
    E rowToEntity(ResultSet res) throws SQLException;

    Optional<E> get(int id);

    Collection<E> getAll();

    void save(E t);

    void update(E t);

    void delete(E t);
}
