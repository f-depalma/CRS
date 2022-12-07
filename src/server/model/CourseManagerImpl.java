package server.model;

import server.database.Connection;
import server.database.DBConnector;
import server.database.dao.CourseDao;
import server.database.dao.FavoriteCourseDao;
import server.database.entity.Course;
import server.database.entity.FavoriteCourse;
import shared.transferobject.dto.FavoriteCourseDTO;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class CourseManagerImpl implements CourseManager {
    private PropertyChangeSupport support;

    public CourseManagerImpl() {
        this.support = new PropertyChangeSupport(this);
    }

    @Override
    public List<Course> getFavoriteCourses(int profileId) {
        CourseDao courseDao = CourseDao.getInstance();
        FavoriteCourseDao favoriteCourseDao = FavoriteCourseDao.getInstance();

        List<String> courseIds = favoriteCourseDao.getAllCourseByProfileId(profileId);
        return courseDao.getAllByIds(courseIds);
    }

    @Override
    public boolean removeFavoriteCourses(List<FavoriteCourse> favoriteCourses) {
        FavoriteCourseDao favoriteCourseDao = FavoriteCourseDao.getInstance();
        boolean ret = true;
        for (FavoriteCourse f : favoriteCourses) {
            ret &= favoriteCourseDao.delete(f);
        }
        return ret;
    }

    @Override
    public List<Course> getAllByNameNotInFavorite(String filter, int profileId) {
        CourseDao courseDao = CourseDao.getInstance();
        return courseDao.getAllByNameNotInFavorite(filter, profileId);
    }

    @Override
    public boolean addFavoriteCourses(List<FavoriteCourse> favoriteCourses) {
        FavoriteCourseDao favoriteCourseDao = FavoriteCourseDao.getInstance();
        boolean ret = true;

        for (FavoriteCourse f : favoriteCourses) {
            ret &= favoriteCourseDao.save(f);
        }
        return ret;
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
