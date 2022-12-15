package server.model;

import server.database.dao.CourseDao;
import server.database.dao.FavoriteCourseDao;
import server.database.entity.Course;
import server.database.entity.FavoriteCourse;

import java.util.List;

public class CourseManagerImpl implements CourseManager {

    private CourseDao courseDao;
    private FavoriteCourseDao favoriteCourseDao;

    public CourseManagerImpl() {
        this.courseDao = CourseDao.getInstance();
        this.favoriteCourseDao = FavoriteCourseDao.getInstance();
    }

    @Override
    public List<Course> getFavoriteCourses(int profileId) {
        List<String> courseIds = favoriteCourseDao.getAllCourseByProfileId(profileId);
        return courseDao.getAllByIds(courseIds);
    }

    @Override
    public boolean removeFavoriteCourses(List<FavoriteCourse> favoriteCourses) {
        boolean ret = true;
        for (FavoriteCourse f : favoriteCourses) {
            ret &= favoriteCourseDao.delete(f);
        }
        return ret;
    }

    @Override
    public List<Course> getAllByNameNotInFavorite(String filter, int profileId) {
        return courseDao.getAllByNameNotInFavorite(filter, profileId);
    }

    @Override
    public boolean addFavoriteCourses(List<FavoriteCourse> favoriteCourses) {
        boolean ret = true;

        for (FavoriteCourse f : favoriteCourses) {
            ret &= favoriteCourseDao.save(f);
        }
        return ret;
    }
}
