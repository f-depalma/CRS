package server.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
    private java.sql.Connection connection;
    private ConnectionStatus status;

    public Connection() {
        this.status = ConnectionStatus.ERROR;
    }

    public Connection(java.sql.Connection connection) {
        this.connection = connection;
        this.status = ConnectionStatus.OPEN;
    }

    public java.sql.Connection getConnection() {
        return connection;
    }

    public ConnectionStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectionStatus status) {
        this.status = status;
    }

    public PreparedStatement prepareStatement(String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public void close() {
        this.status = ConnectionStatus.CLOSED;
    }
}
