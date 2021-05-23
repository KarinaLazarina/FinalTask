package com.epam.FinalTask;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Controller extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        response.setContentType("text/html");
        String commandName = request.getParameter("command");

        // obtain command object by its name
        Command command = CommandContainer.getCommand(commandName);
        // execute command and get forward address
        String forward = command.execute(request, response);
//        response.sendRedirect(forward);
        // if the forward address is not null go to the address
        if (forward != null) {
            RequestDispatcher disp = request.getRequestDispatcher(forward);
            disp.forward(request, response);
        }


//        // Hello
//        PrintWriter out = response.getWriter();
//        out.println("<html><body>");
//        out.println("<h1>" + message + "</h1>");
//        out.println("</body></html>");
    }

    public void destroy() {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter("command");

        // obtain command object by its name
        Command command = CommandContainer.getCommand(commandName);
        // execute command and get forward address
        String forward = command.execute(request, response);
        response.sendRedirect(request.getContextPath() + forward);

        // if the forward address is not null go to the address
//        if (forward != null) {
//            RequestDispatcher disp = request.getRequestDispatcher(forward);
//            disp.forward(request, response);
//        }
    }
//
//    private String doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String commandName = request.getParameter("command");
//
//        // obtain command object by its name
//        Command command = CommandContainer.getCommand(commandName);
//        // execute command and get forward address
//        String forward = command.execute(request, response);
////        response.sendRedirect(forward);
//        return forward;
//        // if the forward address is not null go to the address
////        if (forward != null) {
////            RequestDispatcher disp = request.getRequestDispatcher(forward);
////            disp.forward(request, response);
////        }
//    }
}
