package server.model;

import server.database.entity.TeacherOfCourse;

import java.util.List;

public interface TeacherManager {

    List<TeacherOfCourse> getTeachersByCourse(String courseName);
}
