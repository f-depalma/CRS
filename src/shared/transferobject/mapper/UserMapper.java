package shared.transferobject.mapper;

import server.database.entity.User;
import shared.transferobject.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserMapper implements Mapper<User, UserDTO> {
    private static UserMapper instance = null;

    private UserMapper() {}

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    @Override
    public UserDTO entityToDTO(User entity) {
        return new UserDTO(entity.getUsername(), entity.getPassword());
    }

    @Override
    public User DTOToEntity(UserDTO dto) {
        User entity = new User();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        return entity;
    }

    @Override
    public List<UserDTO> allEntitiesToDTOs(List<User> entities) {
        List<UserDTO> dtos = new ArrayList<>();

        for (User entity : entities) {
            dtos.add(entityToDTO(entity));
        }

        return dtos;
    }

    @Override
    public List<User> allDTOsToEntities(List<UserDTO> dtos) {
        List<User> entities = new ArrayList<>();

        for (UserDTO dto : dtos) {
            entities.add(DTOToEntity(dto));
        }

        return entities;
    }
}
