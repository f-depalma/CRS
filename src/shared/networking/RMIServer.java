package shared.networking;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServer extends Remote {
    void registerCallback(ClientCallback ccb) throws RemoteException;
}
