package server.database;

public class QueriesBook {
    public static final String SELECT_ID_FROM_USER_WHERE_USERNAME_AND_PASSWORD = "select id from app_user where username = ? and password = ?";
    public static final String SELECT_FROM_PROFILE_WHERE_ID = "select * from profile where id = ?";
    public static final String INSERT_INTO_APP_USER_ALL_VALUES = "INSERT INTO app_user(username, password) VALUES (?, ?)";
    public static final String INSERT_INTO_PROFILE_ALL_VALUES =
            "INSERT INTO profile(id, first_name, last_name, email, birthday, type)" +
            "VALUES (?, ?, ?, ?, ?, ?)";
}