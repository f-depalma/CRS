package server.model;

import server.database.dao.ReviewDao;
import server.database.entity.Review;

import java.rmi.RemoteException;
import java.sql.Date;
import java.util.List;

public class ReviewManagerImpl implements ReviewManager {

    private ReviewDao reviewDao = ReviewDao.getInstance();

    @Override
    public List<Review> getAllReviews(String courseName) {
        return reviewDao.getAllByCourseName(courseName);
    }

    @Override
    public boolean saveReview(Review review) {
        review.setDate(new Date(System.currentTimeMillis()));
        return reviewDao.save(review);
    }

    @Override
    public boolean updateReview(Review review) {
        review.setDate(new Date(System.currentTimeMillis()));
        return reviewDao.update(review);
    }
}
