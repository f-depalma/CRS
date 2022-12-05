package server.networking;

import server.model.CourseManager;
import server.model.LoginManager;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;
import shared.transferobject.mapper.CourseMapper;
import shared.transferobject.mapper.FavoriteCourseMapper;
import shared.transferobject.mapper.ProfileMapper;
import shared.transferobject.mapper.UserMapper;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImpl implements RMIServer {

    private UserMapper userMapper;
    private ProfileMapper profileMapper;
    private CourseMapper courseMapper;
    private FavoriteCourseMapper favoriteCourseMapper;

    private LoginManager loginManager;
    private CourseManager courseManager;

    public RMIServerImpl(LoginManager loginManager, CourseManager courseManager) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.userMapper = UserMapper.getInstance();
        this.profileMapper = ProfileMapper.getInstance();
        this.courseMapper = CourseMapper.getInstance();
        this.favoriteCourseMapper = FavoriteCourseMapper.getInstance();

        this.loginManager = loginManager;
        this.courseManager = courseManager;
    }

    public void startServer() {
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
            registry.bind("Server", this);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    // PROFILE
    @Override
    public ProfileDTO login(UserDTO userDTO) {
        return profileMapper.entityToDTO(
                loginManager.login(
                        userMapper.DTOToEntity(userDTO)
                ));
    }

    @Override
    public ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) throws RemoteException {
        return profileMapper.entityToDTO(
                loginManager.createAccount(
                        userMapper.DTOToEntity(userDTO),
                        profileMapper.DTOToEntity(profileDTO)
                ));
    }

    // COURSE
    @Override
    public List<CourseDTO> getFavoriteCourses(int profileId) throws RemoteException {
        return courseMapper.allEntitiesToDTOs(courseManager.getFavoriteCourses(profileId));
    }

    @Override
    public boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) throws RemoteException {
        return courseManager.removeFavoriteCourses(favoriteCourseMapper.allDTOsToEntities(favoriteCourseDTOS));
    }

    @Override
    public List<CourseDTO> getAllByNameNotInFavorite(String filter, int profileId) throws RemoteException {
        return courseMapper.allEntitiesToDTOs(courseManager.getAllByNameNotInFavorite(filter, profileId));
    }

    @Override
    public boolean addFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) throws RemoteException {
        return courseManager.addFavoriteCourses(favoriteCourseMapper.allDTOsToEntities(favoriteCourseDTOS));
    }

    @Override
    public void registerCallback(ClientCallback ccb) throws RemoteException {
        loginManager.addListener("Message", evt -> {});
    }


}
