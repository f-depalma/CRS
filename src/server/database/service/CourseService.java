package server.database.service;

import server.database.entity.Course;
import java.util.List;

public interface CourseService {

    List<Course> getFavoriteCourses(int profileId);

    boolean removeFavoriteCourses(List<Integer> courseIds);

    List<Course> getAllCourses(String filter);

    boolean addFavoriteCourses(List<Integer> courseIds, int profileId);
}
