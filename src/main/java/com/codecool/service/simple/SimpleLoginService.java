package com.codecool.service.simple;

import com.codecool.dao.PoetDao;
import com.codecool.model.Poet;
import com.codecool.service.LoginService;
import com.codecool.service.exception.ServiceException;

import java.sql.SQLException;

public final class SimpleLoginService implements LoginService {

    private final PoetDao poetDao;

    public SimpleLoginService(PoetDao poetDao) {
        this.poetDao = poetDao;
    }

    @Override
    public Poet loginUser(String name, String password) throws SQLException, ServiceException {
        try {
            Poet poet = poetDao.findByName(name);
            if (poet == null || !poet.getPassword().equals(password)) {
                throw new ServiceException("Bad login");
            }
            return poet;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
