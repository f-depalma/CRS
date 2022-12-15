package client.network;

import shared.networking.RMIServer;
import shared.transferobject.dto.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.NotBoundException;
import java.util.List;

public class RMIClient implements Client, Remote {

    private String name;
    private RMIServer server;

    public RMIClient() {
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
    public List<ReviewDTO> getAllReviews(String courseName) {
        try {
            return server.getAllReviews(courseName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean saveReview(ReviewDTO reviewDTO) {
        try {
            return server.saveReview(reviewDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReview(ReviewDTO reviewDTO) {
        try {
            return server.updateReview(reviewDTO);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TeacherOfCourseDTO> getTeachersByCourse(String courseName) {
        try {
            return server.getTeachersByCourse(courseName);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
