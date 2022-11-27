package shared.transferobject.dto;

import shared.util.UserType;
import shared.util.Utils;

import java.io.Serializable;
import java.sql.Date;
import java.util.Locale;

public class ProfileDTO implements Serializable {
    private String name;
    private String lastname;
    private String email;
    private String birthday;
    private UserType.TYPE type;

    public ProfileDTO(String name, String lastname, String email, String birthday, String type) throws Exception {
        this.name = name;
        this.lastname = lastname;
        this.email = Utils.checkEmail(email);
        this.birthday = birthday;
        this.type = UserType.getEnum(Utils.checkProfileType(type));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public UserType.TYPE getType() {
        return type;
    }

    public void setType(UserType.TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProfileDTO{" +
                "name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", type='" + type.name().toLowerCase(Locale.ROOT) + '\'' +
                '}';
    }
}
