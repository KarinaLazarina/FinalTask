package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand implements Command {
    private static final Logger log = Logger.getLogger(LogoutCommand.class);

    private static final String pathToLoginPage = "/";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null)
            session.invalidate();

        log.debug("User logout");
        return pathToLoginPage;
    }
}
