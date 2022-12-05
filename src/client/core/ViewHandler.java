package client.core;

import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {
    private ViewModelFactory vmf;

    private Scene loginScene;
    private Scene accountScene;
    private Scene favoriteCourseScene;
    private Scene courseScene;

    private Stage stage;

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }


    public void start() {
        stage = new Stage();
        openLoginScene();
    }

    public void openLoginScene() {
        if (loginScene == null) {
            try {
                Parent root = loadFXML("../../client/views/login/loginCourse.fxml");

                loginScene = new Scene(root);
                stage.setTitle("Log In");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(loginScene);
        stage.show();
    }

    public void openAccountScene() {
        if (accountScene == null) {
            try {
                Parent root = loadFXML("../../client/views/account/accountCourse.fxml");

                accountScene = new Scene(root);
                stage.setTitle("Create account");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setScene(accountScene);
        stage.show();
    }

    public void openFavoriteCoursesScene() {
        if (favoriteCourseScene == null) {
            try {
                Parent root = loadFXML("../../client/views/favoritecourses/favoriteCourses.fxml");
                favoriteCourseScene = new Scene(root);
                stage.setTitle("Favorite Courses");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        vmf.getFavoriteCoursesVM().getFavoriteCourses();
        stage.setScene(favoriteCourseScene);
        stage.show();
    }

    public void openCoursesScene() {
        if (courseScene == null) {
            try {
                Parent root = loadFXML("../../client/views/courses/courses.fxml");
                courseScene = new Scene(root);
                stage.setTitle("All Courses");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        vmf.getCoursesVM().getAllCourses("");
        stage.setScene(courseScene);
        stage.show();
    }

    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(this, vmf);
        return root;
    }
}
