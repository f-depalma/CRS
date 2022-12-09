package client.views.favoritecourses;

import client.model.Storage;
import client.model.favoritecourses.FavoriteCourses;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.ArrayList;
import java.util.List;


public class FavoriteCoursesVM {
    private ObjectProperty<ObservableList<CourseDTO>> list;

    private FavoriteCourses favoriteCoursesManager;
    private Storage storage = Storage.get();

    public FavoriteCoursesVM(FavoriteCourses favoriteCourses) {
        list = new SimpleObjectProperty<>();
        list.set(new SimpleListProperty<>());
        this.favoriteCoursesManager = favoriteCourses;
    }

    public void removeFavoriteCourses(List<CourseDTO> courseDTOS) {
        List<FavoriteCourseDTO> favoriteCourseDTOS = new ArrayList<>();
        for (CourseDTO c : courseDTOS) {
            FavoriteCourseDTO favoriteCourseDTO = new FavoriteCourseDTO(c.getShortName(), storage.getProfile().getId());
            favoriteCourseDTOS.add(favoriteCourseDTO);
        }

        if (this.favoriteCoursesManager.removeFavoriteCourses(favoriteCourseDTOS))
            getFavoriteCourses();
    }

    public void getFavoriteCourses() {
        list.set(
                FXCollections.observableArrayList(
                        favoriteCoursesManager.getFavoriteCourses(storage.getProfile().getId())
                ));
    }

    public ObjectProperty<ObservableList<CourseDTO>> getListProperty() {
        return list;
    }
}
