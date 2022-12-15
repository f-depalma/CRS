package server.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;

public class DBConnector {
    private static final int MAX_CONNECTION = 5;
    private static final int MIN_CONNECTION_AVAILABLE = 2;

    // Database configuration
    private static final String NAME = "postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String CURRENT_SCHEMA = "crs";
    private static final String PORT = "5431";

    private static Deque<Connection> connections = new ArrayDeque<>();

    public static synchronized Connection getConnection() {
        int toCheck = connections.size();

        while (toCheck > 0) {
            switch (connections.getFirst().getStatus()) {
                case OPEN -> connections.addLast(connections.pop());
                case ERROR -> connections.pop();
                case CLOSED -> {
                    Connection con = connections.pop();
                    con.setStatus(Connection.STATUS.OPEN);
                    connections.addLast(con);
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
        String url = "jdbc:postgresql://localhost:" + PORT + "/" + NAME + "?currentSchema=" + CURRENT_SCHEMA;
        try {
            return new Connection(DriverManager.getConnection(url, USER, PASSWORD));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return new Connection();
    }

    private static void clean() {
        if (connections.size() > MAX_CONNECTION) {
            int closed = 0;

            for (Connection con : connections) {
                if (con.getStatus().equals(Connection.STATUS.CLOSED)) closed++;
            }

            if (closed > MIN_CONNECTION_AVAILABLE) {
                int toRemove = closed - MIN_CONNECTION_AVAILABLE;
                for (Connection con : connections) {
                    if (con.getStatus().equals(Connection.STATUS.CLOSED)) {
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
