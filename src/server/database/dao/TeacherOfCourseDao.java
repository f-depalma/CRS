package server.database.dao;

import server.database.Connection;
import server.database.DBConnector;
import server.database.QueriesBook;
import server.database.entity.TeacherOfCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherOfCourseDao implements Dao<TeacherOfCourse> {
    private static TeacherOfCourseDao instance = null;

    private TeacherOfCourseDao() {
    }

    public static TeacherOfCourseDao getInstance() {
        if (instance == null)
            instance = new TeacherOfCourseDao();
        return instance;
    }
    @Override
    public TeacherOfCourse rowToEntity(ResultSet res) throws SQLException {
        TeacherOfCourse entity = new TeacherOfCourse();
        entity.setCourseName(res.getString("course_name"));
        entity.setProfileId(res.getInt("profile_id"));
        return entity;
    }

    @Override
    public Optional<TeacherOfCourse> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<TeacherOfCourse> getAll() {
        return null;
    }

    public List<TeacherOfCourse> getAllByCourse(String courseName) {
        Connection con = DBConnector.getConnection();
        List<TeacherOfCourse> list = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.SELECT_FROM_TEACHER_OF_COURSE_WHERE_COURSE_NAME);
            statement.setString(1, courseName);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                list.add(rowToEntity(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(TeacherOfCourse t) {
        return false;
    }

    @Override
    public boolean update(TeacherOfCourse t) {
        return false;
    }

    @Override
    public boolean delete(TeacherOfCourse t) {
        return false;
    }
}
