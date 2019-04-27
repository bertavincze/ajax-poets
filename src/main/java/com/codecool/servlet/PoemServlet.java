package com.codecool.servlet;

import com.codecool.dao.PoemDao;
import com.codecool.dao.database.DatabasePoemDao;
import com.codecool.model.Poem;
import com.codecool.service.PoemService;
import com.codecool.service.simple.SimplePoemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/poem")
public final class PoemServlet extends AbstractServlet {

    // https://www.postgresql.org/docs/current/static/errcodes-appendix.html
    private static final String SQL_ERROR_CODE_UNIQUE_VIOLATION = "23505";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Poem poem = (Poem) req.getAttribute("poem");
        sendMessage(resp, HttpServletResponse.SC_OK, poem);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoemDao poemDao = new DatabasePoemDao(connection);
            PoemService poemService = new SimplePoemService(poemDao);

            String title = req.getParameter("title");
            Poem poem = poemService.findByTitle(title);
            req.setAttribute("poem", poem);
            doGet(req, resp);
        } catch (SQLException ex) {
            if (SQL_ERROR_CODE_UNIQUE_VIOLATION.equals(ex.getSQLState())) {
                sendMessage(resp, HttpServletResponse.SC_BAD_REQUEST, "SQL Exception occurred.");
            } else {
                handleSqlError(resp, ex);
            }
        }
    }
}
