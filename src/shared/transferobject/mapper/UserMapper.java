package shared.transferobject.mapper;

import server.database.entity.User;
import shared.transferobject.dto.UserDTO;


public class UserMapper extends Mapper<User, UserDTO> {
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
}
