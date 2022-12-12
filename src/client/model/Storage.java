package client.model;

import shared.transferobject.dto.CourseDTO;
import shared.transferobject.dto.ProfileDTO;
import shared.util.Subject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Subject {

    private PropertyChangeSupport support;
    private static Storage instance;
    private ProfileDTO profile;
    private List<Page> breadcrumb = new ArrayList<>();
    private CourseDTO course;

    private Storage() {
        this.support = new PropertyChangeSupport(this);
    }

    public static Storage get() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public ProfileDTO getProfile() {
        return profile;
    }

    public void setProfile(ProfileDTO profileDTO) {
        profile = profileDTO;
    }

    public void goTo(Page page) {
        breadcrumb.add(page);
        support.firePropertyChange("navigate", null, page);
    }

    public void goBack() {
        breadcrumb.remove(breadcrumb.size() - 1);
        support.firePropertyChange("navigate", null, breadcrumb.get(breadcrumb.size() - 1));
    }

    public void setCourse(CourseDTO course) {
        this.course = course;
    }

    public CourseDTO getCourse() {
        return course;
    }

    @Override
    public void addListener(String eventName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(eventName, listener);
    }

    @Override
    public void removeListener(String eventName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(eventName, listener);
    }
}
