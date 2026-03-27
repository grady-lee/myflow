package com.lee.entity;

import com.lee.enums.Gender;

public class User {

    private long id;
    private String username;
    private String passwordHash;
    private String email;
    private Integer gender;

    public User(long id, String username, String passwordHash, String email, Integer gender) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.gender = gender;
    }

    public User() {
    }

    public User(String username, String passwordHash, String email, Integer gender) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.gender = gender;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                '}';
    }
}
