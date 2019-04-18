package com.codecool.dao;

import com.codecool.model.Poet;

import java.sql.SQLException;

public interface PoetDao {

    Poet findByName(String name) throws SQLException;
}
