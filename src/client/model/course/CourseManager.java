package client.model.course;

import client.network.Client;
import shared.transferobject.dto.ReviewDTO;
import shared.transferobject.dto.TeacherOfCourseDTO;

import java.util.List;

public class CourseManager implements Course {
    private Client client;

    public CourseManager(Client client) {
        this.client = client;
    }

    @Override
    public List<ReviewDTO> getAllReviews(String courseName) {
        return client.getAllReviews(courseName);
    }

    @Override
    public boolean saveReview(ReviewDTO reviewDTO) {
        return client.saveReview(reviewDTO);
    }

    @Override
    public boolean updateReview(ReviewDTO reviewDTO) {
        return client.updateReview(reviewDTO);
    }

    @Override
    public List<TeacherOfCourseDTO> getTeacherByCourseName(String courseName) {
        return client.getTeachersByCourse(courseName);
    }
}
