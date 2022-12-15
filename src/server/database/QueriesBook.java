package server.database;

public class QueriesBook {
    // USER
    public static final String SELECT_ID_FROM_USER_WHERE_USERNAME_AND_PASSWORD = "select id from app_user where username = ? and password = ?";
    public static final String INSERT_INTO_APP_USER_ALL_VALUES = "INSERT INTO app_user(username, password) VALUES (?, ?)";
    // PROFILE
    public static final String SELECT_FROM_PROFILE_WHERE_ID = "select * from profile where id = ?";
    public static final String INSERT_INTO_PROFILE_ALL_VALUES =
            "INSERT INTO profile(id, first_name, last_name, email, birthday, type)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    // FAVORITE COURSE
    public static final String SELECT_COURSE_NAME_FROM_FAVORITE_COURSE_WHERE_PROFILE_ID = "select course_name from favorite_course where profile_id = ?";
    public static final String DELETE_FROM_FAVORITE_COURSE_WHERE_SHORT_NAME_AND_PROFILE_ID = "delete from favorite_course where course_name = ? and profile_id = ?";
    public static final String INSERT_INTO_FAVORITE_COURSE_ALL_VALUES = "insert into favorite_course(course_name, profile_id) values (?, ?)";
    // COURSE
    public static final String SELECT_FROM_COURSE_WHERE_SHORT_NAME_IN = "select * from course where short_name = ANY(?)";
    public static final String SELECT_FROM_COURSE_WHERE_NAME_LIKE_NOT_IN_FAVORITE =
            "SELECT * FROM course where short_name not in" +
            "     (select course_name from favorite_course where profile_id = ?)" +
            "and name like ?";
    // REVIEW
    public static final String SELECT_FROM_REVIEW_WHERE_COURSE_MAME = "select * from review where course_name = ?";
    public static final String INSERT_INTO_REVIEW_ALL_VALUES = "INSERT INTO review(review, rate, review_date, profile_id, course_name) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_REVIEW_SET_REVIEW_RATE_REVIEW_DATE_WHERE_PROFILE_ID_AND_COURSE_NAME = "UPDATE review SET review = ?, rate = ?, review_date = ? where profile_id = ? and course_name = ?";
    //TEACHER OF COURSE
    public static final String SELECT_FROM_TEACHER_OF_COURSE_WHERE_COURSE_NAME = "select * from teacher_of_course where course_name = ?";
}