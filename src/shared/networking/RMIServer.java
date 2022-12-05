package shared.networking;

import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;
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

    boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) throws RemoteException;

    List<CourseDTO> getAllByNameNotInFavorite(String filter, int profileId) throws RemoteException;

    boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) throws RemoteException;

}
