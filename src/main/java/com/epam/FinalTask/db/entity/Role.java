package com.epam.FinalTask.db.entity;

public enum Role {
    ADMIN("admin"),
    DOCTOR("doctor"),
    NURSE("nurse");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {return value;}

    public static Role fromValue(String v) {
        for (Role c: Role.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
