package client.model.account;

import client.network.Client;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AccountManager implements Account {

    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    private Client client;

    public AccountManager(Client client) {
        this.client = client;
        client.startClient();
        //client.addListener("Message", this::fire);
    }

    private void fire(PropertyChangeEvent evt) {
        support.firePropertyChange(evt);
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


