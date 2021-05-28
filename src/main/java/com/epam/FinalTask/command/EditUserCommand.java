package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.PatientDao;
import com.epam.FinalTask.db.dao.UserDao;
import com.epam.FinalTask.db.entity.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class EditUserCommand implements Command {
    //todo:addLoger and validation

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String role = request.getParameter("role_id");
        Map<String, String[]> parameterMap = request.getParameterMap();
        String pathToForm = "/controller?command=getUsers&userName=";


        if ("patient".equals(role)) {
            new PatientDao().editPatient(parameterMap);
            return pathToForm + role;
        } else {
            new UserDao().editUser(parameterMap);
            return pathToForm + Role.fromValue(Integer.parseInt(role)).toString().toLowerCase();

//            request.setAttribute("addRole", Role.fromValue(Integer.parseInt(role)).toString().toLowerCase());
        }
//        if (result) {
//            session.removeAttribute("errors");
//            session.setAttribute("userAdded", "User successfully added!");
//        }
    }
}
