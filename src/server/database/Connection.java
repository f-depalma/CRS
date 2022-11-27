package server.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
    private java.sql.Connection connection;
    private STATUS status;

    public enum STATUS {
        OPEN,
        CLOSED,
        ERROR
    }

    public Connection() {
        this.status = STATUS.ERROR;
    }

    public Connection(java.sql.Connection connection) {
        this.connection = connection;
        this.status = STATUS.OPEN;
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public void close() {
        this.status = STATUS.CLOSED;
    }
}
