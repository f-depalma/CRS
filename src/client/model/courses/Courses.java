package client.model.courses;

import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.List;

public interface Courses {
    List<CourseDTO> getAllCourses(String filter, int profileId);

    boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS);
}
