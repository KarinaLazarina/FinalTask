package com.epam.FinalTask.db.dao;

import com.epam.FinalTask.db.DBManager;
import com.epam.FinalTask.db.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao {
    private static final String SQL_FIND_DOCTOR =
            "SELECT * FROM user WHERE role = 'doctor'";

    public List<Doctor> findDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_DOCTOR);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                doctors.add(parseDoctor(rs));
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
//        } finally {
//            DBManager.getInstance().commitAndClose(con);
//        }
        return doctors;
    }

    private Doctor parseDoctor(ResultSet rs) {
        try {
            Doctor doctor = new Doctor();
            doctor.setId(rs.getInt("id"));
            doctor.setLogin(rs.getString("login"));
            doctor.setPassword(rs.getString("password"));
            doctor.setFirst_name(rs.getString("first_name"));
            doctor.setLast_name(rs.getString("last_name"));
            doctor.setAge(rs.getInt("age"));
            doctor.setSpecialization(rs.getString("specialization"));
            return doctor;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
