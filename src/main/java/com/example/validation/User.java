package com.example.validation;

public final class User {

    private final int code;
    private final String password;

    public User(int code, String password) {
        this.code = code;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return String.format("User{code=%s, password='%s'}", code, password);
    }
}
