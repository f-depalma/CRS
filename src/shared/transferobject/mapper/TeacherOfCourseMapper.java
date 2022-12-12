package shared.transferobject.mapper;

import server.database.dao.ProfileDao;
import server.database.entity.Profile;
import server.database.entity.TeacherOfCourse;
import shared.transferobject.dto.TeacherOfCourseDTO;

import java.util.Optional;

public class TeacherOfCourseMapper extends Mapper<TeacherOfCourse, TeacherOfCourseDTO> {

    private ProfileDao profileDao;
    private static TeacherOfCourseMapper instance;

    private TeacherOfCourseMapper() {
        profileDao = ProfileDao.getInstance();
    }

    public static TeacherOfCourseMapper getInstance() {
        if (instance == null) {
            instance = new TeacherOfCourseMapper();
        }
        return instance;
    }

    @Override
    public TeacherOfCourseDTO entityToDTO(TeacherOfCourse entity) {
        Optional<Profile> teacherOpt = profileDao.get(entity.getProfileId());
        TeacherOfCourseDTO dto = null;
        if (teacherOpt.isPresent()) {
            Profile teacher  = teacherOpt.get();
            dto = new TeacherOfCourseDTO(
                    teacher.getFirstName() + " " + teacher.getLastName(),
                    entity.getProfileId(),
                    entity.getCourseName()
            );
        }
        return dto;
    }

    @Override
    public TeacherOfCourse DTOToEntity(TeacherOfCourseDTO dto) {
        TeacherOfCourse entity = new TeacherOfCourse();
        entity.setProfileId(dto.getTeacherId());
        entity.setCourseName(dto.getCourseName());
        return entity;
    }
}
