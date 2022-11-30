package shared.transferobject.mapper;

import server.database.entity.FavoriteCourse;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.ArrayList;
import java.util.List;

public class FavoriteCourseMapper implements Mapper<FavoriteCourse, FavoriteCourseDTO> {

    private static FavoriteCourseMapper instance = null;

    private FavoriteCourseMapper() {}

    public static FavoriteCourseMapper getInstance() {
        if (instance == null) {
            instance = new FavoriteCourseMapper();
        }
        return instance;
    }

    @Override
    public FavoriteCourseDTO entityToDTO(FavoriteCourse entity) {
        return new FavoriteCourseDTO(entity.getCourseShortName(), entity.getProfileId());
    }

    @Override
    public FavoriteCourse DTOToEntity(FavoriteCourseDTO dto) {
        FavoriteCourse entity = new FavoriteCourse();
        entity.setCourseShortName(dto.getCourseShortName());
        entity.setProfileId(dto.getProfileId());
        return entity;
    }

    @Override
    public List<FavoriteCourseDTO> allEntitiesToDTOs(List<FavoriteCourse> entities) {
        List<FavoriteCourseDTO> dtos = new ArrayList<>();

        for (FavoriteCourse entity : entities) {
            dtos.add(entityToDTO(entity));
        }

        return dtos;
    }

    @Override
    public List<FavoriteCourse> allDTOsToEntities(List<FavoriteCourseDTO> dtos) {
        List<FavoriteCourse> entities = new ArrayList<>();

        for (FavoriteCourseDTO dto : dtos) {
            entities.add(DTOToEntity(dto));
        }

        return entities;
    }
}
