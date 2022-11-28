package server.database.dao;

import server.database.entity.FavoriteCourse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class FavoriteCourseDao implements Dao<FavoriteCourse> {
    // TODO: implements those methods (Sprint 2) + singleton

    @Override
    public FavoriteCourse rowToEntity(ResultSet res) throws SQLException {
        return null;
    }

    @Override
    public Optional<FavoriteCourse> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<FavoriteCourse> getAll() {
        return null;
    }

    @Override
    public void save(FavoriteCourse t) {

    }

    @Override
    public void update(FavoriteCourse t) {

    }

    @Override
    public void delete(FavoriteCourse t) {

    }
}
