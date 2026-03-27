package com.lee.dto;

public class RegisterRequestDTO {

    private String username;
    private String rawPassword;
    private Integer gender;
    private String email;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(Integer gender, String username, String rawPassword, String email) {
        this.gender = gender;
        this.username = username;
        this.rawPassword = rawPassword;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
