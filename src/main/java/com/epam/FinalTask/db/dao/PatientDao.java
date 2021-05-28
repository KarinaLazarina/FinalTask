package com.epam.FinalTask.db.dao;

import com.epam.FinalTask.db.DBManager;
import com.epam.FinalTask.db.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PatientDao {
    private static final String SQL_FIND_PATIENTS =
            "SELECT * FROM patient";
    private static final String SQL_FIND_PATIENT_BY_ID =
            "SELECT * FROM patient where id = ?";
    private static final String SQL_ADD_PATIENT =
            "INSERT INTO patient(first_name, last_name, date_of_birth, doctor_id, status)\n" +
                    "VALUES (?,?,?,?,?);";
    private static final String SQL_EDIT_PATIENT = "UPDATE patient SET " +
            "first_name = ?, last_name = ?, doctor_id = ? " +
            "WHERE id = ?";

    public List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_PATIENTS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                patients.add(parsePatient(rs));
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return patients;
    }

    private Patient parsePatient(ResultSet rs) {
        try {
            Patient patient = new Patient();
            patient.setId(rs.getInt("id"));
            patient.setFirst_name(rs.getString("first_name"));
            patient.setLast_name(rs.getString("last_name"));
            patient.setDate_of_birth(rs.getString("date_of_birth"));
            patient.setStatus(rs.getString("status"));
            patient.setDoctor_id(rs.getInt("doctor_id"));

            return patient;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Patient getPatient(int id) {
        Patient patient = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_PATIENT_BY_ID);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                patient = parsePatient(rs);
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return patient;
    }

    public boolean addNewPatient(Map<String, String[]> params) {
//    public boolean addNewPatient(Patient patient) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL_ADD_PATIENT);) {

            pstmt.setString(1, params.get("first_name")[0]);
            pstmt.setString(2, params.get("last_name")[0]);
            pstmt.setString(3, params.get("date_of_birth")[0]);
            pstmt.setInt(4, Integer.parseInt(params.get("doctor_id")[0]));
            pstmt.setString(5, params.get("status")[0]);


//            pstmt.setString(1, patient.getFirst_name());
//            pstmt.setString(2, patient.getLast_name());
//            pstmt.setString(3, patient.getDate_of_birth());
//            pstmt.setInt(4, patient.getDoctor_id());
//            pstmt.setString(5, patient.getStatus());

            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return false;
    }

    public boolean editPatient(Map<String, String[]> params) {
//    public boolean addNewPatient(Patient patient) {
        try (Connection con = DBManager.getInstance().getConnection();
             PreparedStatement pstmt = con.prepareStatement(SQL_EDIT_PATIENT);) {

            pstmt.setString(1, params.get("first_name")[0]);
            pstmt.setString(2, params.get("last_name")[0]);
            pstmt.setInt(3, Integer.parseInt(params.get("doctor_id")[0]));
            pstmt.setString(4, params.get("id")[0]);


//            pstmt.setString(1, patient.getFirst_name());
//            pstmt.setString(2, patient.getLast_name());
//            pstmt.setString(3, patient.getDate_of_birth());
//            pstmt.setInt(4, patient.getDoctor_id());
//            pstmt.setString(5, patient.getStatus());

            pstmt.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return false;
    }

}
