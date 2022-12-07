package server.database.dao;

import server.database.entity.Program;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class ProgramDao implements Dao<Program> {
    private static ProgramDao instance = null;

    private ProgramDao() {
    }

    public static ProgramDao getInstance() {
        if (instance == null)
            instance = new ProgramDao();
        return instance;
    }


    @Override
    public Program rowToEntity(ResultSet res) throws SQLException {
        return null;
    }

    @Override
    public Optional<Program> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Program> getAll() {
        return null;
    }

    @Override
    public boolean save(Program t) {
        return false;
    }

    @Override
    public boolean update(Program t) {
        return false;
    }

    @Override
    public boolean delete(Program t) {
        return false;
    }
}
