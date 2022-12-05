package client.model.courses;

import client.network.Client;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.List;

public class CoursesManager implements Courses {
    private Client client;

    public CoursesManager(Client client) {
        this.client = client;
    }

    @Override
    public List<CourseDTO> getAllCourses(String filter, int profileId) {
        return client.getAllCourses(filter, profileId);
    }

    @Override
    public boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) {
        return client.addFavoriteCourses(favoriteCourseDTOS);
    }
}
