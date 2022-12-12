package server.model;

import server.database.dao.TeacherOfCourseDao;
import server.database.entity.TeacherOfCourse;

import java.util.List;

public class TeacherManagerImpl implements TeacherManager {
    private TeacherOfCourseDao teacherOfCourseDao;

    public TeacherManagerImpl() {
    this.teacherOfCourseDao = TeacherOfCourseDao.getInstance();
    }

    @Override
    public List<TeacherOfCourse> getTeachersByCourse(String courseName) {
        return teacherOfCourseDao.getAllByCourse(courseName);
    }
}
