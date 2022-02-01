package com.covid.covid.dto;

public class LoginResponseDTO {
    private String userId;
    private String token;
    private String userName;
    private String address;

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "userId='" + userId + '\'' +
                ", token='" + token + '\'' +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
