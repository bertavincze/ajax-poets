package com.codecool.servlet;

import com.codecool.dao.PoemDao;
import com.codecool.dao.database.DatabasePoemDao;
import com.codecool.dto.PoemDto;
import com.codecool.model.Poem;
import com.codecool.model.Poet;
import com.codecool.service.PoemService;
import com.codecool.service.exception.ServiceException;
import com.codecool.service.simple.SimplePoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/profile")
public final class ProfileServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DatabasePoemDao(connection);
            PoemService poemService = new SimplePoemService(poemDao);
            Poet poet = (Poet) req.getSession().getAttribute("poet");
            List<Poem> poems = poemService.getPoems(String.valueOf(poet.getId()));

            PoemDto poemDto = new PoemDto(poet, poems);
            sendMessage(resp, HttpServletResponse.SC_OK, poemDto);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
