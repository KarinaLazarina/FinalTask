package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.UserDao;
import com.epam.FinalTask.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetUsersCommand implements Command {
    private static final String pathToList = "/WEB-INF/admin/listOfUsers.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<User> users = null;
        String userName = request.getParameter("userName");
        request.setAttribute("userName", userName);

        if("doctor".equals(userName)){
            users = new UserDao().getDoctors();
        }
        if("nurse".equals(userName)){
            users = new UserDao().getNurses();
        }
        request.setAttribute("users", users);
        return pathToList;
    }
}
