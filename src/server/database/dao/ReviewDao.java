package server.database.dao;

import server.database.Connection;
import server.database.DBConnector;
import server.database.QueriesBook;
import server.database.entity.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReviewDao implements Dao<Review> {

    private static ReviewDao instance = null;

    private ReviewDao() {
    }

    public static ReviewDao getInstance() {
        if (instance == null)
            instance = new ReviewDao();
        return instance;
    }

    @Override
    public Review rowToEntity(ResultSet res) throws SQLException {
        Review review = new Review();
        review.setReview(res.getString("review"));
        review.setRate(res.getInt("rate"));
        review.setDate(res.getDate("review_date"));
        review.setCourseName(res.getString("course_name"));
        review.setProfileId(res.getInt("profile_id"));
        return review;
    }

    @Override
    public Optional<Review> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    public List<Review> getAllByCourseName(String courseName) {
        Connection con = DBConnector.getConnection();
        List<Review> reviews = new ArrayList<>();

        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.SELECT_FROM_REVIEW_WHERE_COURSE_MAME);
            statement.setString(1, courseName);
            ResultSet res = statement.executeQuery();

            while (res.next()) {
                reviews.add(rowToEntity(res));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        con.close();
        return reviews;
    }

    @Override
    public boolean save(Review t) {
        Connection con = DBConnector.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(QueriesBook.INSERT_INTO_REVIEW_ALL_VALUES);
            setStatement(statement, t);
            if (statement.executeUpdate() <= 0) {
                throw new Exception("Element not saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        con.close();
        return true;
    }

    @Override
    public boolean update(Review t) {
        Connection con = DBConnector.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement(
                    QueriesBook.UPDATE_REVIEW_SET_REVIEW_RATE_REVIEW_DATE_WHERE_PROFILE_ID_AND_COURSE_NAME);
            setStatement(statement, t);
            if (statement.executeUpdate() <= 0) {
                throw new Exception("Element not updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        con.close();
        return true;
    }

    private void setStatement(PreparedStatement statement, Review t) throws SQLException {
        statement.setString(1, t.getReview());
        statement.setInt(2, t.getRate());
        statement.setDate(3, t.getDate());
        statement.setInt(4, t.getProfileId());
        statement.setString(5, t.getCourseName());
    }

    @Override
    public boolean delete(Review t) {
        return false;
    }
}
