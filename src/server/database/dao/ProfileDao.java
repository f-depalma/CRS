package server.database.dao;

import server.database.QueriesBook;
import server.database.DBConnector;
import server.database.entity.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class ProfileDao implements Dao<Profile, Integer> {
    private static ProfileDao instance = null;
    protected static Optional<Connection> connection = null;

    private ProfileDao() {
        connection = DBConnector.getConnection();
    }

    public static ProfileDao getInstance() {
        if (instance == null)
            instance = new ProfileDao();
        return instance;
    }

    @Override
    public Profile rowToEntity(ResultSet res) throws SQLException {
        Profile profile = new Profile();
        profile.setId(res.getInt("id"));
        profile.setFirstName(res.getString("first_name"));
        profile.setLastName(res.getString("last_name"));
        profile.setEmail(res.getString("email"));
        profile.setBirthday(res.getDate("birthday"));
        profile.setType(res.getString("type"));
        return profile;
    }

    @Override
    public Optional<Profile> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Profile> profileOpt = Optional.empty();

            try {
                PreparedStatement statement = conn.prepareStatement(QueriesBook.SELECT_FROM_PROFILE_WHERE_ID);
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    profileOpt = Optional.of(rowToEntity(resultSet));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            return profileOpt;
        });
    }

    @Override
    public Collection<Profile> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Profile profile) {
        return Optional.empty();
    }

    @Override
    public void update(Profile profile) {

    }

    @Override
    public void delete(Profile profile) {

    }
}
