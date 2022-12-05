package server.database.dao;

import server.database.Connection;
import server.database.DBConnector;
import server.database.QueriesBook;
import server.database.entity.Course;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CourseDao implements Dao<Course> {
    // TODO: implements those methods (Sprint 2) + singleton
    private static CourseDao instance = null;

    private CourseDao() {
    }

    public static CourseDao getInstance() {
        if (instance == null)
            instance = new CourseDao();
        return instance;
    }

    @Override
    public Course rowToEntity(ResultSet res) throws SQLException {
        Course course = new Course();
        course.setShortName(res.getString("short_name"));
        course.setName(res.getString("name"));
        course.setProgramShortName(res.getString("program_name"));
        return course;
    }

    @Override
    public Optional<Course> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    public List<Course> getAllByNameNotInFavorite(String filter, int profileId) {
        Connection con = DBConnector.getConnection();
        List<Course> courses = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.SELECT_FROM_COURSE_WHERE_NAME_LIKE_NOT_IN_FAVORITE);
            statement.setInt(1, profileId);
            statement.setString(2, "%" + (filter == null? "": filter) + "%");
            System.out.println(statement);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                courses.add(rowToEntity(res));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
        return courses;
    }

    @Override
    public boolean save(Course t) {
        return false;
    }

    @Override
    public boolean update(Course t) {
        return false;
    }

    @Override
    public boolean delete(Course t) {
        return false;
    }

    public List<Course> getAllByIds(List<String> ids) {
        Connection con = DBConnector.getConnection();
        List<Course> courses = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.SELECT_FROM_COURSE_WHERE_SHORT_NAME_IN);
            Array array = con.createArrayOf("VARCHAR", ids.toArray());
            statement.setArray(1, array);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                courses.add(rowToEntity(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
        return courses;
    }
}
