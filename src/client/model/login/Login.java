package client.model.login;

import shared.transferobject.dto.ProfileDTO;

public interface Login {
    ProfileDTO login(String username, String password);
}


