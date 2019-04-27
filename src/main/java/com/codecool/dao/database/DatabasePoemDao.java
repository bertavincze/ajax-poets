package com.codecool.dao.database;

import com.codecool.dao.PoemDao;
import com.codecool.model.Poem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabasePoemDao extends AbstractDao implements PoemDao {

    public DatabasePoemDao(Connection connection) {
        super(connection);
    }

    @Override
    public List<Poem> findAll(int id) throws SQLException {
        List<Poem> poems = new ArrayList<>();
        String sql = "SELECT id, title, content, poet_id FROM poems WHERE poet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    poems.add(fetchPoem(resultSet));
                }
            }
        }
        return poems;
    }

    @Override
    public Poem findByPoetId(int id) throws SQLException {
        String sql = "SELECT id, title, content FROM poems WHERE poet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchPoem(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public Poem findByTitle(String title) throws SQLException {
        String sql = "SELECT id, title, content, poet_id FROM poems WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchPoem(resultSet);
                }
            }
        }
        return null;
    }

    private Poem fetchPoem(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String content = resultSet.getString("content");
        int poet_id = resultSet.getInt("poet_id");
        return new Poem(id, title, content, poet_id);
    }
}
