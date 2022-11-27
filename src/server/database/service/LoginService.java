package server.database.service;

import server.database.entity.Profile;
import server.database.entity.User;

public interface LoginService {
    Profile login(User user);
    Profile createUser(User user, Profile profile);
}
