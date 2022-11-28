package server.networking;

import server.model.LoginManager;
import server.model.LoginManagerImpl;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;
import shared.transferobject.mapper.ProfileMapper;
import shared.transferobject.mapper.UserMapper;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImpl implements RMIServer {

    private LoginManager loginManager;
    private UserMapper userMapper;
    private ProfileMapper profileMapper;

    public RMIServerImpl(LoginManager loginManager) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.userMapper = UserMapper.getInstance();
        this.profileMapper = ProfileMapper.getInstance();
        this.loginManager = loginManager;
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

    @Override
    public ProfileDTO login(UserDTO userDTO) {
        return profileMapper.entityToDTO(
                loginManager.login(
                        userMapper.DTOToEntity(userDTO)
                ));
    }

    // TODO: implement this method (Sprint 2)
    @Override
    public List<CourseDTO> getFavoriteCourses(int profileId) throws RemoteException {
        return null;
    }

    @Override
    public boolean removeFavoriteCourses(List<Integer> courseIds) throws RemoteException {
        return false;
    }

    @Override
    public List<CourseDTO> getAllCourses(String filter) throws RemoteException {
        return null;
    }

    @Override
    public boolean addFavoriteCourses(List<Integer> courseIds, int profileId) throws RemoteException {
        return false;
    }

    @Override
    public ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) throws RemoteException {
        return profileMapper.entityToDTO(
                loginManager.createAccount(
                        userMapper.DTOToEntity(userDTO),
                        profileMapper.DTOToEntity(profileDTO)
                ));
    }

    @Override
    public void registerCallback(ClientCallback ccb) throws RemoteException {
        loginManager.addListener("Message", evt -> {
        });
    }


}
