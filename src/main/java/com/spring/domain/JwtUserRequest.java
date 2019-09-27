package com.spring.domain;

public class JwtUserRequest {
    private String userName;
    private String password;

    public JwtUserRequest(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public JwtUserRequest() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
