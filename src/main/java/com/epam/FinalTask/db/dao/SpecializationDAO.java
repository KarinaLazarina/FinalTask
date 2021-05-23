package com.epam.FinalTask.db.dao;

import com.epam.FinalTask.db.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class SpecializationDAO {
    private Map<Integer, String> specializations = new LinkedHashMap<>();

    private static final String SQL_FIND_SPECIALIZATION =
            "SELECT * FROM specialization";

    public Map<Integer, String> getSpecializations() {
        getSpecializationsFromDB();
        return specializations;
    }

    public String getSpecializationById(int id) {
        getSpecializationsFromDB();
        return specializations.get(id);
    }

    public Integer getIdByValue(String value) {
        Set<Integer> keySet = getSpecializations().keySet();

        for (Integer key : keySet) {
            String specialization = specializations.get(key);
            if (key != null) {
                if (value.equals(specialization)) {
                    return key;
                }
            }
        }
        return 0;
    }

    public void getSpecializationsFromDB() {
        PreparedStatement pstmt;
        ResultSet rs;
        Connection con;
        try {
            con = DBManager.getInstance().getConnection();
            pstmt = con.prepareStatement(SQL_FIND_SPECIALIZATION);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                specializations.put(rs.getInt("id"), rs.getString("specialization"));
            }
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException ex) {
//            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        }
    }


}
