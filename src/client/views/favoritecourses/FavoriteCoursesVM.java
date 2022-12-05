package client.views.favoritecourses;

import client.core.Storage;
import client.model.favoritecourses.FavoriteCourses;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.ArrayList;
import java.util.List;


public class FavoriteCoursesVM {
    private ObjectProperty<ObservableList<CourseDTO>> list;

    private FavoriteCourses favoriteCoursesManager;

    public FavoriteCoursesVM(FavoriteCourses favoriteCourses) {
        list = new SimpleObjectProperty<>();
        list.set(new SimpleListProperty<>());
        this.favoriteCoursesManager = favoriteCourses;
    }

    public void removeFavoriteCourses(List<CourseDTO> courseDTOS) {
        List<FavoriteCourseDTO> favoriteCourseDTOS = new ArrayList<>();
        for (CourseDTO c : courseDTOS) {
            FavoriteCourseDTO favoriteCourseDTO = new FavoriteCourseDTO(c.getShortName(), Storage.getProfile().getId());
            favoriteCourseDTOS.add(favoriteCourseDTO);
        }

        if (this.favoriteCoursesManager.removeFavoriteCourses(favoriteCourseDTOS))
            getFavoriteCourses();
    }

    public void getFavoriteCourses() {
        System.out.println("load favorite course");
        list.set(
                FXCollections.observableArrayList(
                        favoriteCoursesManager.getFavoriteCourses(Storage.getProfile().getId())
                ));
    }

    public ObjectProperty<ObservableList<CourseDTO>> getListProperty() {
        return list;
    }
}
