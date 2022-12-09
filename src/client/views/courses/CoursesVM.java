package client.views.courses;

import client.model.Storage;
import client.model.courses.Courses;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.util.ArrayList;
import java.util.List;

public class CoursesVM {
    private Storage storage = Storage.get();
    private Courses coursesManager;
    private ObjectProperty<ObservableList<CourseDTO>> list;
    private StringProperty filter;

    public CoursesVM(Courses courses) {
        this.coursesManager = courses;
        filter = new SimpleStringProperty();

        list = new SimpleObjectProperty<>();
        list.set(new SimpleListProperty<>());

        getAllCourses("");

        filter.addListener((observable, oldValue, newValue) -> {
            getAllCourses(newValue);
        });
    }

    public ObjectProperty<ObservableList<CourseDTO>> getListProperty() {
        return list;
    }

    public StringProperty getFilter() {
        return filter;
    }

    public void getAllCourses(String filter) {
        list.set(
                FXCollections.observableArrayList(
                        coursesManager.getAllCourses(filter, storage.getProfile().getId())
                ));
    }

    public boolean addFavoriteCourses(List<CourseDTO> courses) {
        List<FavoriteCourseDTO> favoriteCourseDTOS = new ArrayList<>();
        for(CourseDTO c : courses) {
            FavoriteCourseDTO f = new FavoriteCourseDTO(c.getShortName(), storage.getProfile().getId());
            favoriteCourseDTOS.add(f);
        }
        return coursesManager.addFavoriteCourses(favoriteCourseDTOS);
    }
}
