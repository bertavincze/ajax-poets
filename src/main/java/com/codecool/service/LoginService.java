package com.codecool.service;

import com.codecool.model.Poet;
import com.codecool.service.exception.ServiceException;

import java.sql.SQLException;

public interface LoginService {

    Poet loginUser(String name, String password) throws SQLException, ServiceException;
}
