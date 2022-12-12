package shared.transferobject.mapper;

import server.database.entity.Course;
import shared.transferobject.dto.CourseDTO;

public class CourseMapper extends Mapper<Course, CourseDTO> {

    private static CourseMapper instance = null;

    private CourseMapper() {}

    public static CourseMapper getInstance() {
        if (instance == null) {
            instance = new CourseMapper();
        }
        return instance;
    }

    @Override
    public CourseDTO entityToDTO(Course entity) {
        CourseDTO dto = new CourseDTO(entity.getShortName(), entity.getName(), entity.getProgramShortName());
        dto.setDescription(entity.getDescription());
        dto.setEcts(entity.getEcts());
        dto.setRequirements(entity.getRequirements());
        return dto;
    }

    @Override
    public Course DTOToEntity(CourseDTO dto) {
        Course entity = new Course();
        entity.setShortName(dto.getShortName());
        entity.setName(dto.getName());
        entity.setProgramShortName(dto.getProgramShortName());
        entity.setDescription(dto.getDescription());
        entity.setEcts(dto.getEcts());
        entity.setRequirements(dto.getRequirements());
        return entity;
    }
}
