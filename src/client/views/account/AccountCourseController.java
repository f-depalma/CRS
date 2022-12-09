package client.views.account;

import client.model.Page;
import client.model.Storage;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.transferobject.dto.ProfileDTO;

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

    private AccountVM accountVM;
    private Storage storage = Storage.get();

    @Override
    public void init(ViewModelFactory vmf) {
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
        storage.goBack();
    }

    public void onCreateButton(ActionEvent event) {
        ProfileDTO p = accountVM.createAccount();
        if (p != null) {
            storage.setProfile(p);
            storage.goTo(Page.FAVORITE_COURSES);
        }
    }
}
