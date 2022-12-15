package server.model;

import server.database.dao.ProfileDao;
import server.database.dao.UserDao;
import server.database.entity.Profile;
import server.database.entity.User;

import java.util.Optional;

public class LoginManagerImpl implements LoginManager {

    private ProfileDao profileDao;
    private UserDao userDao;

    public LoginManagerImpl() {
        this.profileDao = ProfileDao.getInstance();
        this.userDao = UserDao.getInstance();
    }

    @Override
    public Profile login(User user) {
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
}
