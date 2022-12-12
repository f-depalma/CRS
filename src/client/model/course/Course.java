package client.model.course;

import shared.transferobject.dto.ReviewDTO;
import shared.transferobject.dto.TeacherOfCourseDTO;

import java.util.List;

public interface Course {

    List<ReviewDTO> getAllReviews(String courseName);

    boolean saveReview(ReviewDTO reviewDTO);

    boolean updateReview(ReviewDTO reviewDTO);

    List<TeacherOfCourseDTO> getTeacherByCourseName(String courseName);
}
