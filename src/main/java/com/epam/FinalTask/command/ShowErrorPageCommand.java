package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowErrorPageCommand implements Command {
    private static final String pathToPage = "/WEB-INF/errorPage.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String errorMessage = request.getParameter("errorMessage");
        System.out.println(errorMessage);

        request.setAttribute("errorMessage", errorMessage);
        return pathToPage;
    }
}
