package client.network;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.beans.PropertyChangeListener;

public class RMIClient implements Client, ClientCallback {

    private PropertyChangeSupport support;
    private String name;
    private RMIServer server;

    public RMIClient() {
        support = new PropertyChangeSupport(this);
        try {
            UnicastRemoteObject.exportObject(this, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startClient() {
        Registry registry = null;
/*        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (RMIServer) registry.lookup("ChatServer");
            server.registerCallback( this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }*/

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
