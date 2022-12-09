package client.network;

import shared.networking.RMIServer;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

import java.beans.PropertyChangeSupport;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RMIClient implements Client, Remote {

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
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProfileDTO login(UserDTO userDTO) {
        try {
            ProfileDTO dto = server.login(userDTO);
            List<CourseDTO> courses = server.getFavoriteCourses(dto.getId());
            System.out.println(courses);
            return dto;
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

    @Override
    public List<CourseDTO> getFavoriteCourses(int profileId) {
        try {
            return server.getFavoriteCourses(profileId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) {
        try {
            return server.removeFavoriteCourses(favoriteCourseDTOS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CourseDTO> getAllCourses(String filter, int profileId) {
        try {
            return server.getAllByNameNotInFavorite(filter, profileId);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) {
        try {
            return server.addFavoriteCourses(favoriteCourseDTOS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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
