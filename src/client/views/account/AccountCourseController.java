package client.views.account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AccountCourseController implements ViewController {

    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public Button studentButton;

    @FXML
    public Button teacherButton;

    @FXML
    public TextField emailField;

    @FXML
    public Button createAccountButton;

    @FXML
    public Label errorLabelAccount;

    @FXML
    public TextField passwordAccountField;

    private ViewHandler viewHandler;
    private AccountVM accountVM;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.viewHandler = vh;
        this.accountVM = vmf.getAccountVM();

        firstNameField.textProperty().bindBidirectional(accountVM.getFName());
        lastNameField.textProperty().bindBidirectional(accountVM.getLName());
        errorLabelAccount.textProperty().bindBidirectional(accountVM.getError());
        emailField.textProperty().bindBidirectional(accountVM.getEmailField());
        passwordAccountField.textProperty().bindBidirectional(accountVM.getPasswordField());
    }

    public void onBackButton(ActionEvent event) throws IOException {
        viewHandler.openLoginScene();
    }

    public void onCreateButton(ActionEvent event) {
        accountVM.createAccount();
    }
}
