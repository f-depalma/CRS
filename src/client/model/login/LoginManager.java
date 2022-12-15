package client.model.login;

import client.network.Client;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginManager implements Login {

    private Client client;

    public LoginManager(Client client) {
        this.client = client;
    }

    @Override
    public ProfileDTO login(String username, String password) {
        UserDTO userDTO = new UserDTO(username, password);
        return client.login(userDTO);
    }
}


