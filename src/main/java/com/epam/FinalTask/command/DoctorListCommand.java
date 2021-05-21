package com.epam.FinalTask.command;

import com.epam.FinalTask.Command;
import com.epam.FinalTask.db.dao.DoctorDao;
import com.epam.FinalTask.db.entity.Doctor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DoctorListCommand implements Command {
    private static final String pathDoctorList = "/WEB-INF/admin/listOfDoctors.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Doctor> doctorList = new DoctorDao().findDoctors();
        System.out.println(doctorList);
        request.setAttribute("doctors", doctorList);
////        RequestDispatcher disp = request.getRequestDispatcher("/WEB-INF/admin/listOfDoctors.jsp");
//        try {
//            disp.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
        return pathDoctorList;
    }
}
