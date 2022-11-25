package server.model;

import server.database.entity.Profile;
import server.database.entity.User;
import server.database.service.LoginService;
import server.database.service.LoginServiceImpl;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginManagerImpl implements LoginManager {

    private PropertyChangeSupport support;
    private LoginService loginService = LoginServiceImpl.getInstance();

    public LoginManagerImpl() {
        support = new PropertyChangeSupport(this);
    }

    @Override
    public Profile login(User user) {
        return loginService.login(user);
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
