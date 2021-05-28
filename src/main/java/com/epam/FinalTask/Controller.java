package com.epam.FinalTask;

import org.apache.log4j.Logger;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Controller extends HttpServlet {
    private static final Logger log = Logger.getLogger(Controller.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.debug("Controller starts: doGet");

        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        Command command = CommandContainer.getCommand(commandName);
        log.trace("Obtained command --> " + command);

        String forward = command.execute(request, response);

        log.debug("Controller finished, now go to forward address --> " + forward);
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }
    }

    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.debug("Controller starts: doPost");

        String commandName = request.getParameter("command");
        log.trace("Request parameter: command --> " + commandName);

        Command command = CommandContainer.getCommand(commandName);
        log.trace("Obtained command --> " + command);

        String redirect = command.execute(request, response);

        log.debug("Controller finished, now go to redirect address --> " + redirect);
        response.sendRedirect(request.getContextPath() + redirect);
    }
}
