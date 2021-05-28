package com.epam.FinalTask.validator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class UserValidator {
    public static Map<String, String> validateUser(Map<String, String[]> params) {
        Map<String, String> errors = new HashMap<>();
        String role = params.get("role_id")[0];
        String first_name = params.get("first_name")[0];
        String last_name = params.get("last_name")[0];

        if (!first_name.matches("[-'\\w]{1,20}")) {
            errors.put("first_name_error", "Invalid first name!");
        }
        if (!last_name.matches("[-'\\w]{1,20}")) {
            errors.put("last_name_error", "Invalid last name!");
        }

        if ("patient".equals(role)) {
            String date_of_birth = params.get("date_of_birth")[0];
            if (Date.valueOf(date_of_birth).toLocalDate().isAfter(LocalDate.now())) {
                errors.put("date_error", "Wrong date");
            }
            return errors;
        }

        int age = Integer.parseInt(params.get("age")[0]);
        String login = params.get("login")[0];
        String password = params.get("password")[0];

        if (!login.matches("[_.\\w0-9]{5,20}")) {
            errors.put("login_error", "Invalid login!");
        }
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            errors.put("password_error", "Password should contains at least one letter and one number, minimum 8 characters");
        }
        if (age < 16 || age > 100) {
            errors.put("age_error", "Wrong value");
        }
        return errors;
    }
}
