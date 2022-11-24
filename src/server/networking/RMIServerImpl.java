package server.networking;

import server.model.LoginManager;
import server.QueriesBook;
import shared.networking.ClientCallback;
import shared.networking.RMIServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RMIServerImpl implements RMIServer {

    private LoginManager loginManager;

    public RMIServerImpl(LoginManager loginManager) throws RemoteException {
        UnicastRemoteObject.exportObject(this, 0);
        this.loginManager = loginManager;
    }

    public void startServer(){
        Registry registry = null;
        try {
            Connection con = DBConnector.getConnection();

            PreparedStatement stat = con.prepareStatement(QueriesBook.SELECT_ALL_USERS);

            ResultSet res = stat.executeQuery();

            if (res.next()) {
                String str = res.getString("username");
                System.out.println(str);
            }

            registry = LocateRegistry.createRegistry(1099);
            registry.bind("LoginServer", this);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void registerCallback(ClientCallback ccb) throws RemoteException {
        loginManager.addListener("Message", evt -> {
        });
    }
}
