package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.PatientDao;
import com.epam.FinalTask.db.dao.UserDao;
import com.epam.FinalTask.db.entity.Role;
import com.epam.FinalTask.validator.UserValidator;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class AddUserCommand implements Command {
    private static final Logger log = Logger.getLogger(AddUserCommand.class);
    //todo:addLoger and validation
//    private static final String pathToUserList = "/controller?command=showHomePage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        boolean result;

        String pathToForm = "/controller?command=getForm&addRole=";
        String role = request.getParameter("role_id");
        Map<String, String[]> parameterMap = request.getParameterMap();

        Map<String, String> errors = UserValidator.validateUser(parameterMap);
        session.setAttribute("errors", errors);


        if (errors.size() == 0) {
            if ("patient".equals(role)) {
                result = new PatientDao().addNewPatient(parameterMap);
            } else {
                result = new UserDao().addNewUser(parameterMap);
//            request.setAttribute("addRole", Role.fromValue(Integer.parseInt(role)).toString().toLowerCase());
            }
            if (result) {
                session.removeAttribute("errors");
                session.setAttribute("userAdded", "User successfully added!");
            }
        }

        if ("patient".equals(role)) {
            return pathToForm + role;
        } else {
            return pathToForm + Role.fromValue(Integer.parseInt(role)).toString().toLowerCase();
        }

    }

}
