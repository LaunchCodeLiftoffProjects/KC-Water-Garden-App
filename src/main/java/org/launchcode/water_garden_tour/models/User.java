package org.launchcode.water_garden_tour.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
//@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message="First name is required.")
    private String fname;

    @NotBlank(message="Last name is required.")
    private String lname;

    @NotBlank(message="Email is required.")
    private String username;

    @NotBlank(message="Password is required.")
    private String password;

    private boolean isActive;

    private String role;

//    private User(){
//    }
//
//    private User(String userName, String password){
//        super();
//        this.username = userName;
//        this.password = password;
//    }

    //@NotBlank
    //private String verifyPassword;

//    private String role;

    public User() {
    }


    public User(String fname, String lname, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.role = "USER";
        this.isActive = true;
        //this.verifyPassword = verifyPassword;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getRole() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }

}

