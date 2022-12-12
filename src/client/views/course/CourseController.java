package client.views.course;

import client.core.ViewModelFactory;
import client.model.Storage;
import client.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shared.transferobject.dto.ReviewDTO;

public class CourseController implements ViewController {
    private CourseVM viewModel;

    @FXML
    Label name;

    @FXML
    Label shortName;

    @FXML
    Label program;

    @FXML
    Label ects;

    @FXML
    Label rating;

    @FXML
    Label teachers;

    @FXML
    Label description;

    @FXML
    Label requirements;

    @FXML
    TextArea review;

    @FXML
    Slider rate;

    @FXML
    TableView<ReviewDTO> reviews;

    @FXML
    Button saveOrUpdate;

    private Storage storage = Storage.get();

    @Override
    public void init(ViewModelFactory vmf) {
        this.viewModel = vmf.getCourseVM();
        name.textProperty().bindBidirectional(viewModel.nameProperty());
        shortName.textProperty().bindBidirectional(viewModel.shortNameProperty());
        program.textProperty().bindBidirectional(viewModel.programProperty());
        ects.textProperty().bindBidirectional(viewModel.ectsProperty());
        rating.textProperty().bindBidirectional(viewModel.ratingProperty());
        teachers.textProperty().bindBidirectional(viewModel.teachersProperty());
        description.textProperty().bindBidirectional(viewModel.descriptionProperty());
        requirements.textProperty().bindBidirectional(viewModel.requirementsProperty());
        review.textProperty().bindBidirectional(viewModel.reviewProperty());
        rate.valueProperty().bindBidirectional(viewModel.rateProperty());
        reviews.itemsProperty().bindBidirectional(viewModel.reviewsProperty());
        saveOrUpdate.textProperty().bindBidirectional(viewModel.saveOrUpdateProperty());
    }

    @FXML
    void onSaveOrUpdate() {
        viewModel.saveOrUpdate();
    }

    @FXML
    void onBack() {
        storage.goBack();
    }
}
