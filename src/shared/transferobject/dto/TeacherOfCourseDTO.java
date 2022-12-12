package shared.transferobject.dto;

import java.io.Serializable;

public class TeacherOfCourseDTO implements Serializable {
    private String teacherName;
    private int teacherId;
    private String courseName;

    public TeacherOfCourseDTO(String teacherName, int teacherId, String courseName) {
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
