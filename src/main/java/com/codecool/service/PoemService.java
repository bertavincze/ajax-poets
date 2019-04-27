package com.codecool.service;

import com.codecool.model.Poem;
import com.codecool.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface PoemService {

    List<Poem> getPoems(String id) throws SQLException;

    Poem getPoem(String id) throws SQLException, ServiceException;

    Poem findByTitle(String title) throws SQLException;
}
