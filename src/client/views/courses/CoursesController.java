package client.views.courses;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CoursesController implements ViewController {

    @FXML
    TableView tableView;

    @FXML
    TextField filter;

    private ViewHandler vh;
    private CoursesVM coursesVM;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.coursesVM = vmf.getCoursesVM();
        tableView.itemsProperty().bindBidirectional(coursesVM.getListProperty());
        filter.textProperty().bindBidirectional(coursesVM.getFilter());
    }

    @FXML
    public void onBack(ActionEvent actionEvent) {
        vh.openFavoriteCoursesScene();
    }

    public void onAddToFavoritesButton(ActionEvent actionEvent) {
        if (coursesVM.addFavoriteCourses(tableView.getSelectionModel().getSelectedItems())) {
            vh.openFavoriteCoursesScene();
            System.out.println("is true");
        }
    }
}
