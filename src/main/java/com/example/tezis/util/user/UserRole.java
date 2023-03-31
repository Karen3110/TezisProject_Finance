package com.example.tezis.util.user;

public enum UserRole {
    SUPER_ADMIN("SUPER_ADMIN", 86400000 /* 1day */),
    ADMIN("ADMIN", 21600000 /* 6hours */),
    USER("USER", 3600000 /* 1hour */);


    String role;
    long validationPeriod;

    UserRole(String role, long validationPeriod) {
        this.role = role;
        this.validationPeriod = validationPeriod;
    }

    public String getCode() {
        return role;
    }

    public long getValidationPeriod() {
        return validationPeriod;
    }
}
