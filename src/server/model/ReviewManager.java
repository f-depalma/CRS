package server.model;

import server.database.entity.Review;

import java.rmi.RemoteException;
import java.util.List;

public interface ReviewManager {
    List<Review> getAllReviews(String courseName);

    boolean saveReview(Review review);

    boolean updateReview(Review review);
}
