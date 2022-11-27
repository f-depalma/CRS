package server.database.dao;

import server.database.Connection;
import server.database.QueriesBook;
import server.database.DBConnector;
import server.database.entity.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public class ProfileDao implements Dao<Profile> {
    private static ProfileDao instance = null;

    private ProfileDao() {
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
        Connection conn = DBConnector.getConnection();
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
        conn.close();
        return profileOpt;
    }

    @Override
    public Collection<Profile> getAll() {
        return null;
    }

    @Override
    public void save(Profile profile) {
        Connection conn = DBConnector.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(QueriesBook.INSERT_INTO_PROFILE_ALL_VALUES);
            statement.setInt(1, profile.getId());
            statement.setString(2, profile.getFirstName());
            statement.setString(3, profile.getLastName());
            statement.setString(4, profile.getEmail());
            statement.setDate(5, profile.getBirthday());
            statement.setString(6, profile.getType());

            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn.close();
    }

    @Override
    public void update(Profile profile) {

    }

    @Override
    public void delete(Profile profile) {

    }
}
