package server.database.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Dao<E> {
    E rowToEntity(ResultSet res) throws SQLException;

    Optional<E> get(int id);

    List<E> getAll();

    boolean save(E t);

    boolean update(E t);

    boolean delete(E t);
}
