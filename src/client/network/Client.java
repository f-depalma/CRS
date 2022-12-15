package client.network;


import shared.transferobject.dto.*;

import java.util.List;

public interface Client {

    void startClient();

    ProfileDTO login(UserDTO userDTO);

    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO);

    List<CourseDTO> getFavoriteCourses(int profileId);

    boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS);

    List<CourseDTO> getAllCourses(String filter, int profileId);

    boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS);

    List<ReviewDTO> getAllReviews(String courseName);

    boolean saveReview(ReviewDTO reviewDTO);

    boolean updateReview(ReviewDTO reviewDTO);

    List<TeacherOfCourseDTO> getTeachersByCourse(String courseName);
}
