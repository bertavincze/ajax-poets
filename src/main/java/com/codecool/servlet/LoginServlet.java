package com.codecool.servlet;

import com.codecool.dao.PoetDao;
import com.codecool.dao.database.DatabasePoetDao;
import com.codecool.model.Poet;
import com.codecool.service.LoginService;
import com.codecool.service.exception.ServiceException;
import com.codecool.service.simple.SimpleLoginService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/login")
public final class LoginServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            PoetDao poetDao = new DatabasePoetDao(connection);
            LoginService loginService = new SimpleLoginService(poetDao);

            String name = req.getParameter("name");
            String password = req.getParameter("password");

            Poet poet = loginService.loginUser(name, password);
            req.getSession().setAttribute("poet", poet);

            sendMessage(resp, HttpServletResponse.SC_OK, poet);
        } catch (ServiceException ex) {
            sendMessage(resp, HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        }
    }
}
