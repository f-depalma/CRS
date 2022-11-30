package shared.transferobject.mapper;

import server.database.entity.Course;
import shared.transferobject.dto.CourseDTO;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper implements Mapper<Course, CourseDTO> {

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
        return new CourseDTO(entity.getShortName(), entity.getName(), entity.getProgramShortName());
    }

    @Override
    public Course DTOToEntity(CourseDTO dto) {
        Course entity = new Course();
        entity.setShortName(dto.getShortName());
        entity.setName(dto.getName());
        entity.setProgramShortName(dto.getProgramShortName());
        return entity;
    }

    @Override
    public List<CourseDTO> allEntitiesToDTOs(List<Course> entities) {
        List<CourseDTO> dtos = new ArrayList<>();

        for (Course entity : entities) {
            dtos.add(entityToDTO(entity));
        }

        return dtos;
    }

    @Override
    public List<Course> allDTOsToEntities(List<CourseDTO> dtos) {
        List<Course> entities = new ArrayList<>();

        for (CourseDTO dto : dtos) {
            entities.add(DTOToEntity(dto));
        }

        return entities;
    }
}
