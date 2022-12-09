package shared.transferobject.mapper;

import server.database.entity.Profile;
import shared.transferobject.dto.ProfileDTO;
import shared.util.UserType;
import shared.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class ProfileMapper implements Mapper<Profile, ProfileDTO> {
    private static ProfileMapper instance = null;

    private ProfileMapper() {}

    public static ProfileMapper getInstance() {
        if (instance == null) {
            instance = new ProfileMapper();
        }
        return instance;
    }

    @Override
    public ProfileDTO entityToDTO(Profile entity) {
        try {
            return new ProfileDTO(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    Utils.DATE_FORMAT.format(entity.getBirthday()),
                    entity.getType()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Profile DTOToEntity(ProfileDTO dto) {
        Profile entity = new Profile();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getName());
        entity.setLastName(dto.getLastname());
        entity.setEmail(dto.getEmail());
        entity.setBirthday(Utils.stringToDate(dto.getBirthday()));
        entity.setType(UserType.getValue(dto.getType()));
        return entity;
    }

    @Override
    public List<ProfileDTO> allEntitiesToDTOs(List<Profile> entities) {
        List<ProfileDTO> dtos = new ArrayList<>();

        for (Profile entity : entities) {
            dtos.add(entityToDTO(entity));
        }

        return dtos;
    }

    @Override
    public List<Profile> allDTOsToEntities(List<ProfileDTO> dtos) {
        List<Profile> entities = new ArrayList<>();

        for (ProfileDTO dto : dtos) {
            entities.add(DTOToEntity(dto));
        }

        return entities;
    }
}
