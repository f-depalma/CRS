package server.model;

import server.database.entity.Review;

import java.rmi.RemoteException;
import java.util.List;

public interface ReviewManager {
    List<Review> getAllReviews(String courseName) throws RemoteException;

    boolean saveReview(Review review) throws RemoteException;

    boolean updateReview(Review review) throws RemoteException;
}
