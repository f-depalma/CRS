package client.views.login;

import client.model.Page;
import client.model.Storage;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import shared.transferobject.dto.ProfileDTO;

import java.io.IOException;

public class LoginCourseController implements ViewController {

    @FXML
    public TextField usernameField;

    @FXML
    public PasswordField passwordField;

    @FXML
    public Button loginButton;

    @FXML
    public Button accountButton;

    @FXML
    public Label errorLabel;

    private LoginVM loginVM;
    private Storage storage = Storage.get();

    @Override
    public void init(ViewModelFactory vmf) {
        this.loginVM = vmf.getLoginVM();

        usernameField.textProperty().bindBidirectional(loginVM.getInputUser());
        passwordField.textProperty().bindBidirectional(loginVM.getInputPassword());
        errorLabel.textProperty().bind(loginVM.getError());
    }

    public void onLoginButton(ActionEvent event) {
        ProfileDTO p = loginVM.logIn();
        if (p != null) {
            storage.setProfile(p);
            storage.goTo(Page.FAVORITE_COURSES);
        }
    }

    public void onCreateAccountButton(ActionEvent event) throws IOException {
        storage.goTo(Page.NEW_ACCOUNT);
    }
}
