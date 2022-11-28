package client.network;

import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

import java.beans.PropertyChangeSupport;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.beans.PropertyChangeListener;
import java.util.List;

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
        try {
            registry = LocateRegistry.getRegistry("localhost", 1099);
            server = (RMIServer) registry.lookup("Server");
            server.registerCallback( this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProfileDTO login(UserDTO userDTO) {
        try {
            return server.login(userDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) {
        try {
            return server.createAccount(userDTO, profileDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    // TODO: implements this methods (Sprint 2)
    @Override
    public List<CourseDTO> getFavoriteCourses(int profileId) {
        return null;
    }

    @Override
    public boolean removeFavoriteCourses(List<Integer> courseIds) {
        return false;
    }

    @Override
    public List<CourseDTO> getAllCourses(String filter) {
        return null;
    }

    @Override
    public boolean addFavoriteCourses(List<Integer> courseIds, int profileId) {
        return false;
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
