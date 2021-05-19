
package com.epam.FinalTask;

import com.epam.FinalTask.db.dao.DoctorDao;
import com.epam.FinalTask.db.entity.Doctor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet("/controller")
public class Controller extends HttpServlet {
    private String message = "Controller";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

//        List<Doctor> doctorList = new DoctorDao().findDoctors();
//        request.setAttribute("doctors", doctorList);
//        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/admin/listOfDoctors.jsp");
//        try {
//            disp.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
