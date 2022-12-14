package client.views.login;

import client.model.account.Account;
import client.model.account.AccountManager;
import client.model.login.LoginManager;
import client.network.RMIClient;
import client.views.account.AccountVM;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.CourseManagerImpl;
import server.model.LoginManagerImpl;
import server.model.ReviewManagerImpl;
import server.model.TeacherManagerImpl;
import server.networking.RMIServerImpl;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class LoginVMTest
{

  LoginVM loginVM;

  @BeforeEach void setUp() throws RemoteException
  {
    RMIClient client = new RMIClient();
    LoginManager loginManager = new LoginManager(client);
    loginVM= new LoginVM(loginManager);

    RMIServerImpl ss = new RMIServerImpl(
        new LoginManagerImpl(),
        new CourseManagerImpl(),
        new ReviewManagerImpl(),
        new TeacherManagerImpl()
    );
    ss.startServer();
    client.startClient();
  }

  @Test void logIn()
  {
    //ARRANGE
    StringProperty username = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    username.bindBidirectional(loginVM.getInputUser());
    password.bindBidirectional(loginVM.getInputPassword());

    //ACTION
    username.setValue("admin");
    password.setValue("adminadmin");

    //ASSERT
    assertNotNull(loginVM.logIn());
  }

  @Test void logInZero()
  {
    //ARRANGE

    //ACTION

    //ASSERT
    assertNull(loginVM.logIn());
  }

  @Test void logInOne()
  {
    //ARRANGE
    StringProperty username = new SimpleStringProperty();
    username.bindBidirectional(loginVM.getInputUser());

    //ACTION
    username.setValue("admin");

    //ASSERT
    assertNull(loginVM.logIn());
  }

  @Test void logInMany()
  {
    //ARRANGE
    StringProperty password = new SimpleStringProperty();
    password.bindBidirectional(loginVM.getInputUser());

    //ACTION
    password.setValue("adminadmin");

    //ASSERT
    assertNull(loginVM.logIn());
  }
}