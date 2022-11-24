package server;

import server.model.LoginManagerImpl;
import server.networking.RMIServerImpl;

import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args) throws RemoteException {

        RMIServerImpl ss = new RMIServerImpl(new LoginManagerImpl());
        ss.startServer();
    }
}
