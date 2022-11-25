package client.model.login;

import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;
import shared.util.Subject;

public interface Login extends Subject {
    ProfileDTO login(String username, String password);
}


