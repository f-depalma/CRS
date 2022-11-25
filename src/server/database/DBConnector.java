package server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class DBConnector {
    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {
        if (connection.isEmpty()) {
            String databaseName = "postgres";
            String databaseUser = "postgres";
            String password = "admin";
            String currentSchema = "crs";
            String url = "jdbc:postgresql://localhost:5432/" + databaseName + "?currentSchema=" + currentSchema;

            try {
                connection = Optional.ofNullable(
                        DriverManager.getConnection(url, databaseUser, password));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Connected to the PostgreSQL server successfully.");
        }

        return connection;
    }
}
