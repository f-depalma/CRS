package client.views.favoritecourses;

import client.core.ViewModelFactory;
import client.model.Page;
import client.model.Storage;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import shared.transferobject.dto.CourseDTO;

public class FavoriteCoursesController implements ViewController {
    @FXML
    private TableView<CourseDTO> tableView;

    private FavoriteCoursesVM favoriteCoursesVM;
    private Storage storage = Storage.get();

    @Override
    public void init(ViewModelFactory vmf) {
        this.favoriteCoursesVM = vmf.getFavoriteCoursesVM();
        tableView.itemsProperty().bindBidirectional(favoriteCoursesVM.getListProperty());
    }

    @FXML
    void onFCAddButton(ActionEvent event) {
        storage.goTo(Page.ALL_COURSES);
    }

    @FXML
    void onRemoveFCButton(ActionEvent event) {
        this.favoriteCoursesVM.removeFavoriteCourses(tableView.getSelectionModel().getSelectedItems());
    }

    @FXML
    void onDetail(ActionEvent event) {
        CourseDTO course = tableView.getSelectionModel().getSelectedItem();
        if (course != null) {
            storage.setCourse(course);
            storage.goTo(Page.COURSE);
        }
    }

}
