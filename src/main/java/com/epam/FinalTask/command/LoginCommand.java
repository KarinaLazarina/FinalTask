package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.Controller;
import com.epam.FinalTask.db.dao.SpecializationDAO;
import com.epam.FinalTask.db.dao.UserDao;
import com.epam.FinalTask.db.entity.Role;
import com.epam.FinalTask.db.entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class LoginCommand implements Command {
    private static final Logger log = Logger.getLogger(LoginCommand.class);

    private static final String pathToHomePage = "/controller?command=showHomePage";
    private static final String pathToErrorPage = "/controller?command=showErrorPage";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        log.debug("Command starts");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        log.trace("Request parameter: login --> " + login);

        String password = request.getParameter("password");

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Login/password cannot be empty");
            log.error("errorMessage --> Password or login is null");
            return pathToErrorPage + "&errorMessage=Login/password cannot be empty";
        }

        User user = new UserDao().findUserByLogin(login);
        log.trace("Found in DB: user --> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            String errorMessage = "Cannot find user with such login/password";
//            request.setAttribute("errorMessage", errorMessage);
            log.error("errorMessage --> " + errorMessage);
            return pathToErrorPage + "&errorMessage=" + errorMessage;
        } else {
            configureSession(session, user);
            Role userRole = Role.fromValue(user.getRole_id());
            log.trace("userRole --> " + userRole);

            log.info("User " + user + " logged as " + userRole.toString().toLowerCase());
            return pathToHomePage;

            // work with i18n
//            String userLocaleName = user.getLocaleName();
//            log.trace("userLocalName --> " + userLocaleName);
//
//            if (userLocaleName != null && !userLocaleName.isEmpty()) {
//                Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", userLocaleName);
//
//                session.setAttribute("defaultLocale", userLocaleName);
//                log.trace("Set the session attribute: defaultLocaleName --> " + userLocaleName);
//
//                log.info("Locale for user: defaultLocale --> " + userLocaleName);
//            }
        }
    }

    private void configureSession(HttpSession session, User user){
        session.setAttribute("user", user);
        log.trace("Set the session attribute: user --> " + user);

        String userRole = Role.fromValue(user.getRole_id()).toString().toLowerCase();
        session.setAttribute("userRole", userRole);
        log.trace("Set the session attribute: userRole --> " + userRole);

        Map<Integer, String> specializations = new SpecializationDAO().getSpecializations();
        session.setAttribute("specializations", specializations);
        log.trace("Set the session attribute: specializations --> " + specializations);

    }
}
