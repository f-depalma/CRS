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

}