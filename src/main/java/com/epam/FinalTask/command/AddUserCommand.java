package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserCommand implements Command {
    private static final String pathToUserList = "/controller?command=showHomePage";
    private static final String pathToForm = "/controller?command=getForm";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean result = new UserDao().addNewUser(request.getParameterMap());
        if (!result) {
            String role = request.getParameter("role");
            request.setAttribute("addRole", role);
            return pathToForm;
        }
        return pathToUserList;
    }
}
