package client.views.login;

import client.model.login.Login;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobject.dto.ProfileDTO;

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


    public ProfileDTO logIn() {
        String username = inputUser.get();
        String password = inputPassword.get();
        if (checker(username) && checker(password)) {
            error.set("");
            return loginManager.login(username, password);
        } else {
            error.set("No matching username or password");
            return null;
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
