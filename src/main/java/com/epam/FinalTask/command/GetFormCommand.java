package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetFormCommand implements Command {
    private static final String pathToForm = "/WEB-INF/admin/addUser.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = request.getParameter("addRole");
        request.setAttribute("addRole", role);
        return pathToForm;
    }
}
