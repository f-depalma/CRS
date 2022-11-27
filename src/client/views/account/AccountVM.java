package client.views.account;

import client.model.account.Account;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

public class AccountVM {
    private StringProperty fName;
    private StringProperty lName;
    private StringProperty emailField;
    private StringProperty error;
    private StringProperty passwordField;
    private BooleanProperty isStudent;
    private BooleanProperty isTeacher;
    private StringProperty birthday;
    private Account accountManager;

    public AccountVM(Account account) {
        this.accountManager = account;
        fName = new SimpleStringProperty();
        lName = new SimpleStringProperty();
        emailField = new SimpleStringProperty();
        error = new SimpleStringProperty();
        passwordField = new SimpleStringProperty();
        isStudent = new SimpleBooleanProperty();
        isTeacher = new SimpleBooleanProperty();
        birthday = new SimpleStringProperty();
    }

    public void createAccount() {
        String fistName = fName.get();
        String lastName = lName.get();
        String email = emailField.get();
        String password = passwordField.get();
        boolean isStudent = this.isStudent.get();
        boolean isTeacher = this.isTeacher.get();
        String birthday = this.birthday.get();

        if (stringChecker(fistName)
                && stringChecker(lastName)
                && stringChecker(email)
                && stringChecker(password)
                && booleanChecker(isStudent || isTeacher)
                && stringChecker(birthday)) {

            error.set("");
            UserDTO userDTO = new UserDTO(email, password);
            try {
                ProfileDTO profileDTO = new ProfileDTO(fistName, lastName, email, birthday,
                        isStudent ? "S" : isTeacher ? "T" : "");
                ProfileDTO p = accountManager.createAccount(userDTO, profileDTO);
                if (p != null) {
                    System.out.println(p);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            error.set("Make sure to fill all the fields");
        }
    }

    public StringProperty getFName() {
        return fName;
    }

    public StringProperty getLName() {
        return lName;
    }

    public StringProperty getEmailField() {
        return emailField;
    }

    public StringProperty getError() {
        return error;
    }

    public StringProperty getPasswordField() {
        return passwordField;
    }

    public StringProperty getBirthday() {
        return birthday;
    }

    public BooleanProperty getIsStudentProperty() {
        return isStudent;
    }

    public BooleanProperty getIsTeacherProperty() {
        return isTeacher;
    }

    public boolean stringChecker(String string) {
        return string != "" && string != null;
    }

    public boolean booleanChecker(Boolean bool) {
        return bool;
    }
}
