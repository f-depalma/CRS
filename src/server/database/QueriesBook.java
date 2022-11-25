package server.database;

public class QueriesBook {
    public static final String SELECT_ID_FROM_USER_WHERE_USERNAME_AND_PASSWORD = "select id from app_user where username = ? and password = ?";
    public static final String SELECT_FROM_PROFILE_WHERE_ID = "select * from profile where id = ?";
}


