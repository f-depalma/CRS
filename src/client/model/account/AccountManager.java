package client.model.account;

import client.network.Client;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AccountManager implements Account {

    private Client client;

    public AccountManager(Client client) {
        this.client = client;
    }

    @Override
    public ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) {
        return client.createAccount(userDTO, profileDTO);
    }
}


