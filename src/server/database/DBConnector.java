package server.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class DBConnector {
    private static int maxConnection = 5;
    private static int minConnectionAvailable = 2;

    private static Deque<Connection> connections = new ArrayDeque<>();

    public static Connection getConnection() {
        int toCheck = connections.size();

        while (toCheck > 0) {
            switch (connections.getFirst().getStatus()) {
                case OPEN -> connections.addLast(connections.pop());
                case ERROR -> connections.pop().close();
                case CLOSED -> {
                    Connection con = connections.pop();
                    con.setStatus(ConnectionStatus.OPEN);
                    clean();
                    return con;
                }
            }
            toCheck--;
        }

        connections.addLast(createConnection());

        return connections.getLast();
    }

    private static Connection createConnection() {
        String databaseName = "postgres";
        String databaseUser = "postgres";
        String password = "admin";
        String currentSchema = "crs";
        String url = "jdbc:postgresql://localhost:5432/" + databaseName + "?currentSchema=" + currentSchema;

        try {
            return new Connection(DriverManager.getConnection(url, databaseUser, password));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new Connection();
    }

    private static void clean() {
        if (connections.size() > maxConnection) {
            int closed = 0;

            for (Connection con : connections) {
                if (con.getStatus().equals(ConnectionStatus.CLOSED)) closed++;
            }

            if (closed > minConnectionAvailable) {
                int toRemove = closed - minConnectionAvailable;
                for (Connection con : connections) {
                    if (con.getStatus().equals(ConnectionStatus.CLOSED)) {
                        connections.remove(con);
                    }
                    toRemove--;
                    if (toRemove == 0) {
                        break;
                    }
                }
            }
        }
    }
}
