package client.views.account;

import client.model.account.AccountManager;
import client.network.RMIClient;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.CourseManagerImpl;
import server.model.LoginManagerImpl;
import server.model.ReviewManagerImpl;
import server.model.TeacherManagerImpl;
import server.networking.RMIServerImpl;
import shared.transferobject.dto.ProfileDTO;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class AccountVMTest
{
  AccountVM vm;

  @BeforeEach void setUp() throws RemoteException
  {
    RMIClient client = new RMIClient();
    AccountManager accountManager = new AccountManager(client);
    vm = new AccountVM(accountManager);

    RMIServerImpl ss = new RMIServerImpl(
        new LoginManagerImpl(),
        new CourseManagerImpl(),
        new ReviewManagerImpl(),
        new TeacherManagerImpl()
    );
    ss.startServer();
    client.startClient();
  }

  @Test void createAccountZero()
  {
    //ARRANGE
    StringProperty errorProperty = new SimpleStringProperty();
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    ProfileDTO p = vm.createAccount();
    String error = vm.getError().toString();

    //ASSERT
    assertTrue(errorProperty.toString().equals(error));
  }

  @Test void createAccountOne()
  {
    //ARRANGE
    StringProperty fName = new SimpleStringProperty();
    StringProperty errorProperty = new SimpleStringProperty();
    fName.bindBidirectional(vm.getFName());
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    fName.setValue("Pedro");
    vm.createAccount();
    String error = vm.getError().toString();

    //ASSERT
    assertTrue(errorProperty.toString().equals(error));
  }

  @Test void createAccountMany()
  {
    //ARRANGE
    StringProperty fName = new SimpleStringProperty();
    StringProperty birthday = new SimpleStringProperty();
    BooleanProperty bool = new SimpleBooleanProperty();
    StringProperty errorProperty = new SimpleStringProperty();
    fName.bindBidirectional(vm.getFName());
    birthday.bindBidirectional(vm.getBirthday());
    bool.bindBidirectional(vm.getIsStudentProperty());
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    fName.setValue("Pedro");
    birthday.setValue("2/14/2003");
    bool.setValue(true);
    vm.createAccount();
    String error = vm.getError().toString();


    //ASSERT
    assertTrue(errorProperty.toString().equals(error));
  }


  @Test void createAccount()
  {
    //ARRANGE
    StringProperty fName = new SimpleStringProperty();
    StringProperty lName = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty birthday = new SimpleStringProperty();
    BooleanProperty bool = new SimpleBooleanProperty();
    BooleanProperty boolT = new SimpleBooleanProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty errorProperty = new SimpleStringProperty();

    fName.bindBidirectional(vm.getFName());
    lName.bindBidirectional(vm.getLName());
    password.bindBidirectional(vm.getPasswordField());
    birthday.bindBidirectional(vm.getBirthday());
    email.bindBidirectional(vm.getEmailField());
    bool.bindBidirectional(vm.getIsStudentProperty());
    boolT.bindBidirectional(vm.getIsTeacherProperty());
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    fName.setValue("Pedro");
    lName.setValue("Marsiglia");
    password.setValue("akakajaja14");
    bool.setValue(true);
    boolT.setValue(false);
    birthday.setValue("2/14/2003");
    email.setValue("akaklol@gmail.com");
    ProfileDTO p = vm.createAccount();

    //ASSERT
    assertNotNull(p);
  }

  @Test void createAccountBoundaries()
  {
    //ARRANGE
    StringProperty fName = new SimpleStringProperty();
    StringProperty lName = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty birthday = new SimpleStringProperty();
    BooleanProperty bool = new SimpleBooleanProperty();
    BooleanProperty boolT = new SimpleBooleanProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty errorProperty = new SimpleStringProperty();

    fName.bindBidirectional(vm.getFName());
    lName.bindBidirectional(vm.getLName());
    password.bindBidirectional(vm.getPasswordField());
    birthday.bindBidirectional(vm.getBirthday());
    email.bindBidirectional(vm.getEmailField());
    bool.bindBidirectional(vm.getIsStudentProperty());
    boolT.bindBidirectional(vm.getIsTeacherProperty());
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    fName.setValue("Pedro");
    lName.setValue("Marsiglia");
    password.setValue("akakajaja14");
    bool.setValue(true);
    boolT.setValue(true);
    birthday.setValue("2/14/2003");
    email.setValue("akaklol@gmail.com");
    ProfileDTO p = vm.createAccount();
    String error = vm.getError().toString();

    //ASSERT
    assertTrue(errorProperty.toString().equals(error));

  }

  @Test void createAccountEmailFormat()
  {
    //ARRANGE
    StringProperty fName = new SimpleStringProperty();
    StringProperty lName = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty birthday = new SimpleStringProperty();
    BooleanProperty bool = new SimpleBooleanProperty();
    BooleanProperty boolT = new SimpleBooleanProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty errorProperty = new SimpleStringProperty();

    fName.bindBidirectional(vm.getFName());
    lName.bindBidirectional(vm.getLName());
    password.bindBidirectional(vm.getPasswordField());
    birthday.bindBidirectional(vm.getBirthday());
    email.bindBidirectional(vm.getEmailField());
    bool.bindBidirectional(vm.getIsStudentProperty());
    boolT.bindBidirectional(vm.getIsTeacherProperty());
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    fName.setValue("Pedro");
    lName.setValue("Marsiglia");
    password.setValue("akakajaja14");
    bool.setValue(true);
    boolT.setValue(false);
    birthday.setValue("2/14/2003");
    email.setValue("akaklolgmail.com");
    ProfileDTO p = vm.createAccount();
    String error = vm.getError().toString();

    //ASSERT
    assertTrue(errorProperty.toString().equals(error));

  }

  @Test void createAccountPasswordSize()
  {
    //ARRANGE
    StringProperty fName = new SimpleStringProperty();
    StringProperty lName = new SimpleStringProperty();
    StringProperty password = new SimpleStringProperty();
    StringProperty birthday = new SimpleStringProperty();
    BooleanProperty bool = new SimpleBooleanProperty();
    BooleanProperty boolT = new SimpleBooleanProperty();
    StringProperty email = new SimpleStringProperty();
    StringProperty errorProperty = new SimpleStringProperty();

    fName.bindBidirectional(vm.getFName());
    lName.bindBidirectional(vm.getLName());
    password.bindBidirectional(vm.getPasswordField());
    birthday.bindBidirectional(vm.getBirthday());
    email.bindBidirectional(vm.getEmailField());
    bool.bindBidirectional(vm.getIsStudentProperty());
    boolT.bindBidirectional(vm.getIsTeacherProperty());
    errorProperty.bindBidirectional(vm.getError());

    //ACT
    fName.setValue("Pedro");
    lName.setValue("Marsiglia");
    password.setValue("a");
    bool.setValue(true);
    boolT.setValue(false);
    birthday.setValue("2/14/2003");
    email.setValue("akaklol@gmail.com");
    ProfileDTO p = vm.createAccount();
    String error = vm.getError().toString();


    //ASSERT
    assertTrue(errorProperty.toString().equals(error));
  }

}