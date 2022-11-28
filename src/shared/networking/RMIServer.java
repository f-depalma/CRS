package shared.networking;

import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RMIServer extends Remote {
    ProfileDTO login(UserDTO userDTO) throws RemoteException;

    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) throws RemoteException;

    void registerCallback(ClientCallback ccb) throws RemoteException;

    List<CourseDTO> getFavoriteCourses(int profileId) throws RemoteException;

    boolean removeFavoriteCourses(List<Integer> courseIds) throws RemoteException;

    List<CourseDTO> getAllCourses(String filter) throws RemoteException;

    boolean addFavoriteCourses(List<Integer> courseIds, int profileId) throws RemoteException;

}
