package server.database.dao;

import server.database.Connection;
import server.database.QueriesBook;
import server.database.DBConnector;
import server.database.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class UserDao implements Dao<User> {
    private static UserDao instance = null;

    private UserDao() {
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
        Connection conn = DBConnector.getConnection();
        Optional<Integer> idOpt = Optional.empty();

        try {
            PreparedStatement statement = conn.prepareStatement(QueriesBook.SELECT_ID_FROM_USER_WHERE_USERNAME_AND_PASSWORD);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                idOpt = Optional.of(resultSet.getInt("id"));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return idOpt;
    }


    @Override
    public Collection<User> getAll() {
        return null;
    }

    @Override
    public void save(User user) {
        Connection conn = DBConnector.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(QueriesBook.INSERT_INTO_APP_USER_ALL_VALUES);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
