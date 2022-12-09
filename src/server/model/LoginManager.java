package server.model;

import server.database.entity.Profile;
import server.database.entity.User;

public interface LoginManager {
    Profile login(User user);
    Profile createAccount(User user, Profile profile);
}
