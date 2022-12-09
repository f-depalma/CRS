package server.model;

import server.database.entity.Course;
import server.database.entity.FavoriteCourse;

import java.util.List;

public interface CourseManager {

    List<Course> getFavoriteCourses(int profileId);

    boolean removeFavoriteCourses(List<FavoriteCourse> favoriteCourses);

    List<Course> getAllByNameNotInFavorite(String filter, int profileId);

    boolean addFavoriteCourses(List<FavoriteCourse> favoriteCourseDTOS);
}
