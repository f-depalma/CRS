package server.database.dao;

import server.database.QueriesBook;
import server.database.DBConnector;
import server.database.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class UserDao implements Dao<User, Integer> {
    private static UserDao instance = null;
    protected static Optional<Connection> connection = null;

    private UserDao() {
        connection = DBConnector.getConnection();
    }

    public static UserDao getInstance() {
        if (instance == null)
            instance = new UserDao();
        return instance;
    }

    @Override
    public User rowToEntity(ResultSet res) throws SQLException {
        User user = new User();
        user.setId(res.getInt("id"));
        user.setUsername(res.getString("username"));
        user.setPassword(res.getString("password"));
        return user;
    }

    @Override
    public Optional<User> get(int id) {
        return Optional.empty();
    }

    public Optional<Integer> getIdByUsernameAndPassword(String username, String password) {
        return connection.flatMap(conn -> {
            Optional<Integer> idOpt = Optional.empty();

            try {
                PreparedStatement statement = conn.prepareStatement(QueriesBook.SELECT_ID_FROM_USER_WHERE_USERNAME_AND_PASSWORD);
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    idOpt = Optional.of(resultSet.getInt("id"));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return idOpt;
        });
    }


    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(User user) {
        return Optional.empty();
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
