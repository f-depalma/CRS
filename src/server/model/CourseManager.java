package server.model;

import server.database.entity.Course;
import server.database.entity.FavoriteCourse;
import shared.util.Subject;

import java.util.List;

public interface CourseManager extends Subject {

    List<Course> getFavoriteCourses(int profileId);

    boolean removeFavoriteCourses(List<FavoriteCourse> favoriteCourses);

    List<Course> getAllCourses(String filter);

    boolean addFavoriteCourses(List<String> courseNames, int profileId);
}
