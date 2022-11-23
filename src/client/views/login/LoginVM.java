package client.views.login;

import client.model.login.Login;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LoginVM {

    private StringProperty inputUser;
    private StringProperty inputPassword;
    private StringProperty error;
    private Login loginManager;

    public LoginVM(Login login) {
        this.loginManager = login;
        error = new SimpleStringProperty();
        inputUser = new SimpleStringProperty();
        inputPassword = new SimpleStringProperty();
    }


    public void logIn() {
        String user = inputUser.get();
        String password = inputPassword.get();
        if (checker(user) && checker(password)) {
            error.set("");
        } else {
            error.set("No matching username or password");
        }
    }

    public StringProperty getInputUser() {
        return inputUser;
    }

    public StringProperty getInputPassword() {
        return inputPassword;
    }

    public StringProperty getError() {
        return error;
    }

    public boolean checker(String string) {
        return string != "" && string != null;
    }
}
