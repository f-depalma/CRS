package server.networking;

import server.model.LoginManager;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements RMIServer {

    private LoginManager loginManager;

    public RMIServerImpl(LoginManager loginManager) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.loginManager = loginManager;
    }

    public void startServer(){
        Registry registry = null;
        try {
            registry = LocateRegistry.createRegistry(1099);
            registry.bind("LoginServer", this);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void registerCallback(ClientCallback ccb) throws RemoteException {
        loginManager.addListener("Message", evt -> {
        });
    }
}
