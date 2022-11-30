package server.model;

import server.database.Connection;
import server.database.DBConnector;
import server.database.dao.CourseDao;
import server.database.dao.FavoriteCourseDao;
import server.database.entity.Course;
import server.database.entity.FavoriteCourse;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class CourseManagerImpl implements CourseManager {

    // TODO: implement those methods (Sprint 2) + singleton
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
    public List<Course> getAllCourses(String filter) {
        CourseDao courseDao = CourseDao.getInstance();
        return courseDao.getAllByName(filter);
    }

    @Override
    public boolean addFavoriteCourses(List<String> courseNames, int profileId) {
        FavoriteCourseDao favoriteCourseDao = FavoriteCourseDao.getInstance();
        Connection con = DBConnector.getConnection();
        boolean ret = true;

        for (String courseName : courseNames) {
            FavoriteCourse f = new FavoriteCourse();
            f.setCourseShortName(courseName);
            f.setProfileId(profileId);
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
