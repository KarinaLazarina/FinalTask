package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.UserDao;
import com.epam.FinalTask.db.entity.Role;
import com.epam.FinalTask.db.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {
//    private static final String pathToDoctorPage = "/WEB-INF/admin/listOfDoctors.jsp";
    private static final String pathToHomePage = "/controller?command=showHomePage";
//    private static final String pathToNursePage = "/WEB-INF/admin/listOfDoctors.jsp";
    private static final String pathToErrorPage = "/WEB-INF/errorPage.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();

        // obtain login and password from the request
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        // error handler
//        String errorMessage = null;
//        String forward = Path.PAGE__ERROR_PAGE;

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            System.out.println("password or login is null");
//            errorMessage = "Login/password cannot be empty";
//            request.setAttribute("errorMessage", errorMessage);
            return pathToErrorPage;
        }

        User user = new UserDao().findUserByLogin(login);
        if (user == null || !password.equals(user.getPassword())) {
            System.out.println("wrong user");
//            errorMessage = "Cannot find user with such login/password";
//            request.setAttribute("errorMessage", errorMessage);
            return pathToErrorPage;
        } else {
            configureSession(session, user);
            Role userRole = user.getRole();
            return pathToHomePage;

//            if (userRole == Role.ADMIN)
//                return pathToAdminPage;
//
//            if (userRole == Role.DOCTOR)
//                return pathToDoctorPage;
//
//            if (userRole == Role.NURSE)
//                return pathToNursePage;

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
        session.setAttribute("userRole", user.getRole());
    }
}
