package client.network;


import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.transferobject.dto.UserDTO;
import shared.util.Subject;

import java.util.List;

public interface Client extends Subject {

    void startClient();

    ProfileDTO login(UserDTO userDTO);

    ProfileDTO createAccount(UserDTO userDTO, ProfileDTO profileDTO);

    List<CourseDTO> getFavoriteCourses(int profileId);

    boolean removeFavoriteCourses(List<Integer> courseIds);

    List<CourseDTO> getAllCourses(String filter);

    boolean addFavoriteCourses(List<Integer> courseIds, int profileId);
}
