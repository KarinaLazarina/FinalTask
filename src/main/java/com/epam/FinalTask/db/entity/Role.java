package com.epam.FinalTask.db.entity;

public enum Role {
    ADMIN(1),
    DOCTOR(2),
    NURSE(3);


    private Integer value;

    Role(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Role fromValue(int v) {
        for (Role c : Role.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(Integer.toString(v));
    }
}
