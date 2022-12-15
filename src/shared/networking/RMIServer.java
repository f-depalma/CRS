package shared.networking;

import shared.transferobject.dto.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote {
    ProfileDTO login(UserDTO userDTO) throws RemoteException;

    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) throws RemoteException;

    List<CourseDTO> getFavoriteCourses(int profileId) throws RemoteException;

    boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) throws RemoteException;

    List<CourseDTO> getAllByNameNotInFavorite(String filter, int profileId) throws RemoteException;

    boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) throws RemoteException;

    List<ReviewDTO> getAllReviews(String courseName) throws RemoteException;

    boolean saveReview(ReviewDTO reviewDTO) throws RemoteException;

    boolean updateReview(ReviewDTO reviewDTO) throws RemoteException;

    List<TeacherOfCourseDTO> getTeachersByCourse(String courseName) throws RemoteException;
}
