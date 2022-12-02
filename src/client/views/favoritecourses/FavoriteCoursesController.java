package client.views.favoritecourses;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.views.ViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import server.database.entity.Course;

public class FavoriteCoursesController implements ViewController
{
    @FXML
    private ListView<Course> listView;
    @FXML
    private Button addFCButton;
    @FXML
    private Button removeFCButton;

    @FXML
    void onFCAddButton(ActionEvent event) {
      //TODO: Open Courses view
    }

    @FXML
    void onRemoveFCButton(ActionEvent event) {
      //TODO:
    }
  @Override public void init(ViewHandler vh, ViewModelFactory vmf)
  {
    //TODO:
  }
}
