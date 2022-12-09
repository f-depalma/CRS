package server.networking;

import server.model.CourseManager;
import server.model.LoginManager;
import server.model.ReviewManager;
import shared.networking.RMIServer;
import shared.transferobject.dto.*;
import shared.transferobject.mapper.*;

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
    private ReviewMapper reviewMapper;

    private LoginManager loginManager;
    private CourseManager courseManager;
    private ReviewManager reviewManager;

    public RMIServerImpl(
            LoginManager loginManager,
            CourseManager courseManager,
            ReviewManager reviewManager
    ) throws RemoteException {

        UnicastRemoteObject.exportObject(this, 0);
        this.userMapper = UserMapper.getInstance();
        this.profileMapper = ProfileMapper.getInstance();
        this.courseMapper = CourseMapper.getInstance();
        this.favoriteCourseMapper = FavoriteCourseMapper.getInstance();
        this.reviewMapper = ReviewMapper.getInstance();

        this.loginManager = loginManager;
        this.courseManager = courseManager;
        this.reviewManager = reviewManager;
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

    // REVIEW
    @Override
    public List<ReviewDTO> getAllReviews(String courseName) throws RemoteException {
        return reviewMapper.allEntitiesToDTOs(reviewManager.getAllReviews(courseName));
    }

    @Override
    public boolean saveReview(ReviewDTO reviewDTO) throws RemoteException {
        return reviewManager.saveReview(reviewMapper.DTOToEntity(reviewDTO));
    }

    @Override
    public boolean updateReview(ReviewDTO reviewDTO) throws RemoteException {
        return reviewManager.updateReview(reviewMapper.DTOToEntity(reviewDTO));
    }
}
