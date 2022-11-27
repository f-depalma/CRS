package server.model;

import server.database.entity.Profile;
import server.database.entity.User;
import shared.util.Subject;

public interface LoginManager extends Subject {
    Profile login(User user);
    Profile createAccount(User user, Profile profile);
}
