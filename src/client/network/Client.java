package client.network;


import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;
import shared.util.Subject;

public interface Client extends Subject {

    void startClient();

    ProfileDTO login(UserDTO userDTO);
}
