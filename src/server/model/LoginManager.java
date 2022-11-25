package server.model;

import server.database.entity.Profile;
import server.database.entity.User;
import shared.util.Subject;

import java.sql.SQLException;

public interface LoginManager extends Subject {
    Profile login(User user);
}
