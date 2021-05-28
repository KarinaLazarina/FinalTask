package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.PatientDao;
import com.epam.FinalTask.db.dao.UserDao;
import com.epam.FinalTask.db.entity.Patient;
import com.epam.FinalTask.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetEditFormCommand implements Command {
    private static final Logger log = Logger.getLogger(GetEditFormCommand.class);

    private static final String pathToUserForm = "/WEB-INF/admin/editUser.jsp";
    private static final String pathToPatientForm = "/WEB-INF/admin/editPatient.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = request.getParameter("editRole");
        int id = Integer.parseInt(request.getParameter("userId"));

        request.setAttribute("editRole", role);
        log.trace("Set the request attribute: editRole --> " + role);

        if ("patient".equals(role)) {
            Patient patient = new PatientDao().getPatient(id);
            log.trace("Found in DB: patient --> " + patient);

            request.setAttribute("patient", patient);
            log.trace("Set the request attribute: patient --> " + patient);
            return pathToPatientForm;
        }

        User user = new UserDao().getUser(id);
        log.trace("Found in DB: user --> " + user);

        request.setAttribute("user", user);
        log.trace("Set the request attribute: user --> " + user);
        return pathToUserForm;
    }
}
