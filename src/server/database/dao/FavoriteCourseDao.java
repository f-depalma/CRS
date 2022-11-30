package server.database.dao;

import server.database.Connection;
import server.database.DBConnector;
import server.database.QueriesBook;
import server.database.entity.FavoriteCourse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class FavoriteCourseDao implements Dao<FavoriteCourse> {
    // TODO: implements those methods (Sprint 2) + singleton
    private static FavoriteCourseDao instance = null;

    private FavoriteCourseDao() {
    }

    public static FavoriteCourseDao getInstance() {
        if (instance == null)
            instance = new FavoriteCourseDao();
        return instance;
    }

    @Override
    public FavoriteCourse rowToEntity(ResultSet res) throws SQLException {
        return null;
    }

    @Override
    public Optional<FavoriteCourse> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<FavoriteCourse> getAll() {
        return null;
    }

    @Override
    public boolean save(FavoriteCourse t) {
        Connection con = DBConnector.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.INSERT_INTO_FAVORITE_COURSE_ALL_VALUES);
            statement.setString(1, t.getCourseShortName());
            statement.setInt(2, t.getProfileId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        con.close();
        return true;
    }

    @Override
    public boolean update(FavoriteCourse t) {
        return false;
    }

    @Override
    public boolean delete(FavoriteCourse t) {
        Connection con = DBConnector.getConnection();

        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.DELETE_FROM_FAVORITE_COURSE_WHERE_SHORT_NAME_AND_PROFILE_ID);
            statement.setString(1, t.getCourseShortName());
            statement.setInt(2, t.getProfileId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        con.close();
        return true;
    }

    public List<String> getAllCourseByProfileId(int profileId) {
        Connection con = DBConnector.getConnection();
        List<String> courseIds = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.SELECT_COURSE_NAME_FROM_FAVORITE_COURSE_WHERE_PROFILE_ID);
            statement.setInt(1, profileId);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                courseIds.add(res.getString("course_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
        return courseIds;
    }
}
