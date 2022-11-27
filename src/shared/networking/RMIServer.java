package shared.networking;

import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    ProfileDTO login(UserDTO userDTO) throws RemoteException;
    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO) throws RemoteException;
    void registerCallback(ClientCallback ccb) throws RemoteException;
}
