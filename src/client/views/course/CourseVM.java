package client.views.course;

import client.model.Storage;
import client.model.course.Course;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import shared.transferobject.dto.ReviewDTO;
import shared.transferobject.dto.TeacherOfCourseDTO;

import java.util.List;
import java.util.Optional;

public class CourseVM {
    private Course courseManager;
    private StringProperty name;
    private StringProperty shortName;
    private StringProperty program;
    private StringProperty ects;
    private StringProperty rating;
    private StringProperty teachers;
    private StringProperty description;
    private StringProperty requirements;
    private StringProperty review;
    private IntegerProperty rate;
    private ObjectProperty<ObservableList<ReviewDTO>> reviews;
    private StringProperty saveOrUpdate;
    private boolean isSave = false;
    private Storage storage = Storage.get();

    public CourseVM(Course courses) {
        this.courseManager = courses;
        name = new SimpleStringProperty();
        shortName = new SimpleStringProperty();
        program = new SimpleStringProperty();
        ects = new SimpleStringProperty();
        rating = new SimpleStringProperty();
        teachers = new SimpleStringProperty();
        description = new SimpleStringProperty();
        requirements = new SimpleStringProperty();
        review = new SimpleStringProperty();
        rate = new SimpleIntegerProperty();
        reviews = new SimpleObjectProperty<>();
        reviews.set(new SimpleListProperty<>());
        saveOrUpdate = new SimpleStringProperty();
    }

    public void loadData() {
        clear();
        List<ReviewDTO> reviewDTOS = this.courseManager.getAllReviews(storage.getCourse().getShortName());
        double sum = 0;
        for (ReviewDTO r : reviewDTOS) {
            sum += r.getRate();
        }

        rating.setValue(String.valueOf(sum / reviewDTOS.size()));
        name.setValue(storage.getCourse().getName());
        shortName.setValue(storage.getCourse().getShortName());
        program.setValue(storage.getCourse().getProgramShortName());
        ects.setValue(String.valueOf(storage.getCourse().getEcts()));
        description.setValue(storage.getCourse().getDescription());
        requirements.setValue(storage.getCourse().getRequirements());

        List<TeacherOfCourseDTO> teachers = courseManager.getTeacherByCourseName(storage.getCourse().getShortName());
        String teacherNames = "";
        if (teachers.size() > 0) {
            for (TeacherOfCourseDTO t : teachers) {
                teacherNames += t.getTeacherName() + "\n";
            }
            this.teachers.setValue(teacherNames.substring(0, teacherNames.length() - 2));
        }

        List<ReviewDTO> reviews = courseManager.getAllReviews(storage.getCourse().getShortName());
        Optional<ReviewDTO> myReviewOpt = reviews
                .stream()
                .filter(r -> r.getProfileId() == storage.getProfile().getId())
                .findFirst();

        if (myReviewOpt.isPresent()) {
            ReviewDTO myReview = myReviewOpt.get();
            reviews.remove(myReview);
            review.setValue(myReview.getReview());
            rate.setValue(myReview.getRate());
            isSave = false;
            saveOrUpdate.setValue("Update");
        } else {
            isSave = true;
            saveOrUpdate.setValue("Save");
        }
        this.reviews.set(FXCollections.observableArrayList(reviews));
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty shortNameProperty() {
        return shortName;
    }

    public StringProperty programProperty() {
        return program;
    }

    public StringProperty ectsProperty() {
        return ects;
    }

    public StringProperty ratingProperty() {
        return rating;
    }

    public StringProperty teachersProperty() {
        return teachers;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty requirementsProperty() {
        return requirements;
    }

    public StringProperty reviewProperty() {
        return review;
    }

    public IntegerProperty rateProperty() {
        return rate;
    }

    public ObjectProperty<ObservableList<ReviewDTO>> reviewsProperty() {
        return reviews;
    }

    public StringProperty saveOrUpdateProperty() {
        return saveOrUpdate;
    }

    public void saveOrUpdate() {
        ReviewDTO dto = new ReviewDTO(
                review.get(),
                rate.get(),
                "",
                storage.getCourse().getShortName(),
                storage.getProfile().getId(),
                ""
        );

        if (isSave)
            courseManager.saveReview(dto);
        else
            courseManager.updateReview(dto);
    }

    public void clear() {
        rating.setValue(null);
        name.setValue(null);
        shortName.setValue(null);
        program.setValue(null);
        ects.setValue(null);
        this.teachers.setValue(null);
        isSave = true;
        review.setValue(null);
        rate.setValue(null);
        isSave = false;
    }
}
