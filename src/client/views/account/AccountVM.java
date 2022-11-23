package client.views.account;

import client.model.account.Account;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccountVM
{
  private StringProperty fName;
  private StringProperty lName;
  private StringProperty emailField;
  private StringProperty error;
  private StringProperty passwordField;
  private Account accountManager;

  public AccountVM(Account account)
  {
    this.accountManager = account;
    fName=new SimpleStringProperty();
    lName=new SimpleStringProperty();
    emailField=new SimpleStringProperty();
    error=new SimpleStringProperty();
    passwordField=new SimpleStringProperty();
  }

  public void createAccount()
  {
    String fistName = fName.get();
    String lastName = lName.get();
    String email = emailField.get();
    String password = passwordField.get();

    if(checker(fistName)&&checker(lastName)&&checker(email)&&checker(password))
    {
      error.set("");
    }
    else
    {
      error.set("Make sure to fill all the fields");
    }
  }

  public StringProperty getFName()
  {
    return fName;
  }

  public StringProperty getLName()
  {
    return lName;
  }

  public StringProperty getEmailField()
  {
    return emailField;
  }

  public StringProperty getError()
  {
    return error;
  }

  public StringProperty getPasswordField()
  {
    return passwordField;
  }

  public boolean checker(String string)
  {
    return string!=""&&string!=null;
  }
}
