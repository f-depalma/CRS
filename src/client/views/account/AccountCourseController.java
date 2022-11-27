package client.views.account;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class AccountCourseController implements ViewController {

    @FXML
    public TextField firstNameField;

    @FXML
    public TextField lastNameField;

    @FXML
    public RadioButton studentButton;

    @FXML
    public RadioButton teacherButton;

    @FXML
    public TextField emailField;

    @FXML
    public Button createAccountButton;

    @FXML
    public Label errorLabelAccount;

    @FXML
    public TextField passwordAccountField;

    @FXML
    public ToggleGroup profileType;

    @FXML
    public DatePicker birthday;

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
        studentButton.selectedProperty().bindBidirectional(accountVM.getIsStudentProperty());
        teacherButton.selectedProperty().bindBidirectional(accountVM.getIsTeacherProperty());
        birthday.getEditor().textProperty().bindBidirectional(accountVM.getBirthday());
    }

    public void onBackButton(ActionEvent event) throws IOException {
        viewHandler.openLoginScene();
    }

    public void onCreateButton(ActionEvent event) {
        accountVM.createAccount();
    }
}
