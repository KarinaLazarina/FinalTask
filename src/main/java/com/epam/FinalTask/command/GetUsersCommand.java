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
import java.util.List;

public class GetUsersCommand implements Command {
    private static final Logger log = Logger.getLogger(GetUsersCommand.class);

    private static final String pathToUserList = "/WEB-INF/admin/listOfUsers.jsp";
    private static final String pathToPatientList = "/WEB-INF/admin/listOfPatients.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Command starts");
        List<User> users = null;
        String userName = request.getParameter("userName");
        request.setAttribute("userName", userName);

        if ("patient".equals(userName)) {
            List<Patient> patients = new PatientDao().getPatients();
            log.trace("Found in DB: patients --> " + patients);

            request.setAttribute("patients", patients);
            log.trace("Set the request attribute: patients --> " + patients);
            log.debug("Command finished");
            return pathToPatientList;
        }

        if ("doctor".equals(userName)) {
            String specialization = request.getParameter("specialization");
            if (specialization != null) {
                users = new UserDao().getDoctors(specialization);
            } else {
                users = new UserDao().getDoctors();
            }
            log.trace("Found in DB: doctors --> " + users);
        }
        if ("nurse".equals(userName)) {
            users = new UserDao().getNurses();
            log.trace("Found in DB: nurses --> " + users);
        }

        request.setAttribute("users", users);
        log.trace("Set the request attribute: users --> " + users);
        log.debug("Command finished");
        return pathToUserList;
    }
}
