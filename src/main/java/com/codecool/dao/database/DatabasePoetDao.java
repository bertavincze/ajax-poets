package com.codecool.dao.database;

import com.codecool.dao.PoetDao;
import com.codecool.model.Poet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabasePoetDao extends AbstractDao implements PoetDao {

    public DatabasePoetDao(Connection connection) {
        super(connection);
    }

    public List<Poet> findAll() throws SQLException {
        String sql = "SELECT id, name, password FROM poets";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<Poet> poets = new ArrayList<>();
            while (resultSet.next()) {
                poets.add(fetchUser(resultSet));
            }
            return poets;
        }
    }

    @Override
    public Poet findByName(String name) throws SQLException {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        String sql = "SELECT id, name, password FROM poets WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    private Poet fetchUser(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String password = resultSet.getString("password");
        return new Poet(id, name, password);
    }
}
