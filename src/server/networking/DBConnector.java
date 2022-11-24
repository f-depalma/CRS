package server.networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    public static Connection databaseLink;

    public static Connection getConnection() {
        if (databaseLink == null) {
            String databaseName = "postgres";
            String databaseUser = "postgres";
            String databasePassword = "admin";
            String currentSchema = "crs";
            String url = "jdbc:postgresql://localhost:5432/" + databaseName + "?currentSchema=" + currentSchema;

            try {
                Class.forName("org.postgresql.Driver");
                databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return databaseLink;
    }
}
