package client.model.account;

import server.database.entity.Profile;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;
import shared.util.Subject;

public interface Account extends Subject {
    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO);
}


