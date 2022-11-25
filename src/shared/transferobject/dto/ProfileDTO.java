package shared.transferobject.dto;

import shared.util.Utils;

import java.io.Serializable;
import java.sql.Date;

public class ProfileDTO implements Serializable {
    private String name;
    private String lastname;
    private String email;
    private Date birthday;
    private String type;

    public ProfileDTO(String name, String lastname, String email, Date birthday, String type) throws Exception {
        this.name = name;
        this.lastname = lastname;
        this.email = Utils.checkEmail(email);
        this.birthday = birthday;
        this.type = Utils.checkProfileType(type);
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
