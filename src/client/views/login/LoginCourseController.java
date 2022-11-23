package client.views.login;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

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
    private ViewHandler viewHandler;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.loginVM = vmf.getLoginVM();
        this.viewHandler = vh;

        usernameField.textProperty().bindBidirectional(loginVM.getInputUser());
        passwordField.textProperty().bindBidirectional(loginVM.getInputPassword());
        errorLabel.textProperty().bind(loginVM.getError());
    }

    public void onLoginButton(ActionEvent event) {
        loginVM.logIn();
    }

    public void onCreateAccountButton(ActionEvent event) throws IOException {
        viewHandler.openAccountScene();
    }
}
