package com.ecreatic.test.model;

import java.util.Optional;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int active = 0;
    private String role;
    private String resetToken;


    public User(int id, String FirstName, String LastName, String Email, String Password) {
        this.id = id;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.email = Email;
        this.password = Password;

    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                '}';
    }

    public int getActive() {
        int active = 1;
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
