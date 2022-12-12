package client.views.courses;

import client.core.ViewModelFactory;
import client.model.Page;
import client.model.Storage;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import shared.transferobject.dto.CourseDTO;

public class CoursesController implements ViewController {

    @FXML
    TableView<CourseDTO> tableView;

    @FXML
    TextField filter;

    private CoursesVM coursesVM;
    private Storage storage = Storage.get();

    @Override
    public void init(ViewModelFactory vmf) {
        this.coursesVM = vmf.getCoursesVM();
        tableView.itemsProperty().bindBidirectional(coursesVM.getListProperty());
        filter.textProperty().bindBidirectional(coursesVM.getFilter());
    }

    @FXML
    public void onBack(ActionEvent actionEvent) {
        storage.goBack();
    }

    public void onAddToFavoritesButton(ActionEvent actionEvent) {
        if (coursesVM.addFavoriteCourses(tableView.getSelectionModel().getSelectedItems())) {
            storage.goBack();
        }
    }

    @FXML
    void onDetail(ActionEvent event) {
        storage.setCourse(tableView.getSelectionModel().getSelectedItem());
        storage.goTo(Page.COURSE);
    }
}
