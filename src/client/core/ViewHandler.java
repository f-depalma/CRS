package client.core;

import client.model.Page;
import client.model.Storage;
import client.views.ViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.io.IOException;

public class ViewHandler {
    private ViewModelFactory vmf;

    private Scene loginScene;
    private Scene accountScene;
    private Scene favoriteCourseScene;
    private Scene coursesScene;
    private Scene courseScene;

    private Stage stage;
    private Storage storage = Storage.get();

    public ViewHandler(ViewModelFactory vmf) {
        this.vmf = vmf;
    }


    public void start() {
        stage = new Stage();
        storage.addListener("navigate", this::navigate);
        storage.goTo(Page.LOGIN);
    }

    private void navigate(PropertyChangeEvent evt) {
        switch ((Page) evt.getNewValue()) {
            case LOGIN -> openLoginScene();
            case NEW_ACCOUNT -> openAccountScene();
            case FAVORITE_COURSES -> openFavoriteCoursesScene();
            case ALL_COURSES -> openCoursesScene();
            case COURSE -> openCourseScene();
        }
    }

    private void openLoginScene() {
        if (loginScene == null) {
            try {
                Parent root = loadFXML("../../client/views/login/loginCourse.fxml");
                loginScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setTitle("Log In");
        stage.setScene(loginScene);
        stage.show();
    }

    private void openAccountScene() {
        if (accountScene == null) {
            try {
                Parent root = loadFXML("../../client/views/account/accountCourse.fxml");
                accountScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setTitle("Create account");

        stage.setScene(accountScene);
        stage.show();
    }

    private void openFavoriteCoursesScene() {
        if (favoriteCourseScene == null) {
            try {
                Parent root = loadFXML("../../client/views/favoritecourses/favoriteCourses.fxml");
                favoriteCourseScene = new Scene(root);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setTitle("Favorite Courses");
        vmf.getFavoriteCoursesVM().getFavoriteCourses();
        stage.setScene(favoriteCourseScene);
        stage.show();
    }

    private void openCoursesScene() {
        if (coursesScene == null) {
            try {
                Parent root = loadFXML("../../client/views/courses/courses.fxml");
                coursesScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setTitle("All Courses");
        vmf.getCoursesVM().getAllCourses("");
        stage.setScene(coursesScene);
        stage.show();
    }

    private void openCourseScene() {
        if (courseScene == null) {
            try {
                Parent root = loadFXML("../../client/views/courses/courses.fxml");
                courseScene = new Scene(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        stage.setTitle("Course");
        vmf.getCoursesVM().getAllCourses("");
        stage.setScene(courseScene);
        stage.show();
    }

    private Parent loadFXML(String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        Parent root = loader.load();

        ViewController ctrl = loader.getController();
        ctrl.init(vmf);
        return root;
    }
}
