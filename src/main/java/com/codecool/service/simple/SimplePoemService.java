package com.codecool.service.simple;

import com.codecool.dao.PoemDao;
import com.codecool.model.Poem;
import com.codecool.service.PoemService;
import com.codecool.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public final class SimplePoemService implements PoemService {

    private final PoemDao poemDao;

    public SimplePoemService(PoemDao poemDao) {
        this.poemDao = poemDao;
    }

    @Override
    public List<Poem> getPoems(String id) throws SQLException {
        return poemDao.findAll(Integer.parseInt(id));
    }

    @Override
    public Poem getPoem(String id) throws SQLException, ServiceException {
        try {
            return poemDao.findByPoetId(Integer.parseInt(id));
        } catch (NumberFormatException ex) {
            throw new ServiceException("Poem id must be an integer");
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    @Override
    public Poem findByTitle(String title) throws SQLException {
        return poemDao.findByTitle(title);
    }
}
