package com.lee.dto;

public class LoginResponseDTO {
    private long id;
    private String username;
    private Integer gender;
    private String email;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(Integer gender, String username, String email) {
        this.gender = gender;
        this.username = username;
        this.email = email;
    }

    public LoginResponseDTO(long id, String username, Integer gender, String email) {
        this.id = id;
        this.username = username;
        this.gender = gender;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginResponseDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
