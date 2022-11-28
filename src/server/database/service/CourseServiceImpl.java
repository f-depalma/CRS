package server.database.service;

import server.database.entity.Course;

import java.util.List;

public class CourseServiceImpl implements CourseService{

    // TODO: implement those methods (Sprint 2) + singleton
    @Override
    public List<Course> getFavoriteCourses(int profileId) {
        return null;
    }

    @Override
    public boolean removeFavoriteCourses(List<Integer> courseIds) {
        return false;
    }

    @Override
    public List<Course> getAllCourses(String filter) {
        return null;
    }

    @Override
    public boolean addFavoriteCourses(List<Integer> courseIds, int profileId) {
        return false;
    }
}
