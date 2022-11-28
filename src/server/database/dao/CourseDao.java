package server.database.dao;

import server.database.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class CourseDao implements Dao<Course> {
    // TODO: implements those methods (Sprint 2) + singleton

    @Override
    public Course rowToEntity(ResultSet res) throws SQLException {
        return null;
    }

    @Override
    public Optional<Course> get(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<Course> getAll() {
        return null;
    }

    @Override
    public void save(Course t) {

    }

    @Override
    public void update(Course t) {

    }

    @Override
    public void delete(Course t) {

    }
}
