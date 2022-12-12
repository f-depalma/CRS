package shared.transferobject.mapper;

import server.database.entity.FavoriteCourse;
import shared.transferobject.dto.FavoriteCourseDTO;

public class FavoriteCourseMapper extends Mapper<FavoriteCourse, FavoriteCourseDTO> {

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
}
