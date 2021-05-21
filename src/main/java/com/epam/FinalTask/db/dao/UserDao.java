package com.epam.FinalTask.db.dao;

import com.epam.FinalTask.db.DBManager;
import com.epam.FinalTask.db.entity.Doctor;
import com.epam.FinalTask.db.entity.Role;
import com.epam.FinalTask.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserDao {
    private static final String SQL_FIND_UER_BY_LOGIN =
            "SELECT * FROM user WHERE login = ?";

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

    private User parseUser(ResultSet rs) {
        try {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setAge(rs.getInt("age"));
            user.setRole(Role.fromValue(rs.getString("role")));
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
