package client.model.favoritecourses;

import client.network.Client;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.List;

public class FavoriteCoursesManager implements FavoriteCourses {

    private Client client;

    public FavoriteCoursesManager(Client client) {
        this.client = client;
    }

    @Override
    public List<CourseDTO> getFavoriteCourses(int profileId) {
        return client.getFavoriteCourses(profileId);
    }

    @Override
    public boolean removeFavoriteCourses(List<FavoriteCourseDTO> favoriteCourseDTOS) {
        return client.removeFavoriteCourses(favoriteCourseDTOS);
    }
}
