package com.codecool.dao;

import com.codecool.model.Poem;

import java.sql.SQLException;
import java.util.List;

public interface PoemDao {

    List<Poem> findAll(int id) throws SQLException;

    Poem findByPoetId(int id) throws SQLException;
}
