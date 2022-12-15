package client.model.account;

import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;

public interface Account {
    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO);
}


