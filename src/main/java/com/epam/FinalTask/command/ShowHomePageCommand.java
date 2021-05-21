package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowHomePageCommand implements Command {
    private static final String pathToPage = "/WEB-INF/homePage.jsp";
    private static final String pathToErrorPage = "/WEB-INF/errorPage.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(request.getSession().getAttribute("userRole") == null) return  pathToErrorPage;
        return pathToPage;
    }
}
