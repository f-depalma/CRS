package server;

import server.model.CourseManagerImpl;
import server.model.LoginManagerImpl;
import server.model.ReviewManagerImpl;
import server.networking.RMIServerImpl;

import java.rmi.RemoteException;

public class RunServer {
    public static void main(String[] args) throws RemoteException {

        RMIServerImpl ss = new RMIServerImpl(
                new LoginManagerImpl(),
                new CourseManagerImpl(),
                new ReviewManagerImpl()
        );
        ss.startServer();
    }
}
