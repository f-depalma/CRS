package server.model;

import server.database.dao.ProfileDao;
import server.database.dao.UserDao;
import server.database.entity.Profile;
import server.database.entity.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Optional;

public class LoginManagerImpl implements LoginManager {
    private PropertyChangeSupport support;

    public LoginManagerImpl() {
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public Profile login(User user) {
        ProfileDao profileDao = ProfileDao.getInstance();
        UserDao userDao = UserDao.getInstance();
        Profile profile = null;

        Optional<Integer> userIdOpt = userDao.getIdByUsernameAndPassword(user.getUsername(), user.getPassword());

        if (userIdOpt.isPresent()) {
            Optional<Profile> profileOpt = profileDao.get(userIdOpt.get());

            if (profileOpt.isPresent()) {
                profile = profileOpt.get();
            }
        }

        return profile;
    }

    @Override
    public Profile createAccount(User user, Profile profile) {
        UserDao userDao = UserDao.getInstance();
        ProfileDao profileDao = ProfileDao.getInstance();
        userDao.save(user);

        Optional<Integer> userIdOpt = userDao.getIdByUsernameAndPassword(user.getUsername(), user.getPassword());


        if (userIdOpt.isPresent()) {
            int userId = userIdOpt.get();
            profile.setId(userId);
            profileDao.save(profile);
            Optional<Profile> profileOpt = profileDao.get(userId);
            if (profileOpt.isPresent()) {
                return profileOpt.get();
            }
        }
        return null;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
