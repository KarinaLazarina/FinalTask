package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetFormCommand implements Command {
    private static final Logger log = Logger.getLogger(GetFormCommand.class);

    private static final String pathToUserForm = "/WEB-INF/admin/addUser.jsp";
    private static final String pathToPatientForm = "/WEB-INF/admin/addPatient.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = request.getParameter("addRole");

        request.setAttribute("addRole", role);
        log.trace("Set the request attribute: addRole --> " + role);

        if("patient".equals(role)) return pathToPatientForm;
        return pathToUserForm;
    }
}
