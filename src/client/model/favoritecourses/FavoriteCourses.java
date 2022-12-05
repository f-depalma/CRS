package client.model.favoritecourses;

import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.List;

public interface FavoriteCourses {
    List<CourseDTO> getFavoriteCourses(int profileId);
    boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS);
}
