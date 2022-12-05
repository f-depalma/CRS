package client.views.favoritecourses;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class FavoriteCoursesController implements ViewController {
    @FXML
    private TableView tableView;

    private ViewHandler vh;
    private FavoriteCoursesVM favoriteCoursesVM;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) {
        this.vh = vh;
        this.favoriteCoursesVM = vmf.getFavoriteCoursesVM();
        tableView.itemsProperty().bindBidirectional(favoriteCoursesVM.getListProperty());
    }

    @FXML
    void onFCAddButton(ActionEvent event) {
        vh.openCoursesScene();
    }

    @FXML
    void onRemoveFCButton(ActionEvent event) {
        this.favoriteCoursesVM.removeFavoriteCourses(tableView.getSelectionModel().getSelectedItems());
    }

}
