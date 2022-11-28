package server.database.dao;

import server.database.entity.Programm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class ProgrammDao implements Dao<Programm> {
    // TODO: implements those methods (Sprint 2) + singleton
    @Override
    public Programm rowToEntity(ResultSet res) throws SQLException {
        return null;
    }

    @Override
    public Optional<Programm> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Programm> getAll() {
        return null;
    }

    @Override
    public void save(Programm t) {

    }

    @Override
    public void update(Programm t) {

    }

    @Override
    public void delete(Programm t) {

    }
}
