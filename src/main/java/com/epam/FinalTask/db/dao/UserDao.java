package com.epam.FinalTask.db.dao;

import com.epam.FinalTask.db.DBManager;
import com.epam.FinalTask.db.entity.Role;
import com.epam.FinalTask.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UserDao {
    private static final String SQL_FIND_UER_BY_LOGIN =
            "SELECT * FROM user WHERE login = ?";
    private static final String SQL_FIND_DOCTORS =
            "SELECT * FROM user WHERE role_id = 2";
    private static final String SQL_FIND_NURSES =
            "SELECT * FROM user WHERE role_id = 3";
    private static final String SQL_ADD_USER =
            "INSERT INTO user(login, password, role_id, first_name, last_name, age, doctor_specialization_id)\n" +
                    "VALUES (?,?,?,?,?,?,?);";

    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_UER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = parseUser(rs);
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
        return user;
    }

    public List<User> getDoctors() {
        List<User> doctors = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_DOCTORS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                doctors.add(parseUser(rs));
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return doctors;
    }

    public List<User> getNurses() {
        List<User> nurses = new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_NURSES);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                nurses.add(parseUser(rs));
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return nurses;
    }

    public boolean addNewUser(Map<String, String[]> params) {
        PreparedStatement pstmt;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_ADD_USER);
            pstmt.setString(1, params.get("login")[0]);
            pstmt.setString(2, params.get("password")[0]);
            pstmt.setInt(3, Integer.parseInt(params.get("role_id")[0]));
            pstmt.setString(4, params.get("first_name")[0]);
            pstmt.setString(5, params.get("last_name")[0]);
            pstmt.setString(6, params.get("age")[0]);
            pstmt.setInt(7, new SpecializationDAO().getIdByValue(params.get("specialization")[0]));

            pstmt.executeUpdate();

            pstmt.close();
            con.commit();
            con.close();
            return true;
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
        return false;
    }

    private User parseUser(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setAge(rs.getInt("age"));

            int role_id = rs.getInt("role_id");
            user.setRole_id(role_id);
            if (Role.DOCTOR.getValue().equals(role_id)) {
                user.setSpecialization_id(rs.getInt("doctor_specialization_id"));
            }
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
