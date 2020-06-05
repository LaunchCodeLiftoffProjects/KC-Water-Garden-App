package org.launchcode.water_garden_tour.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.launchcode.water_garden_tour.models.garden.Feature;
import org.launchcode.water_garden_tour.models.garden.Garden;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message="First name is required.")
    @Size(max=15)
    private String fname;

    @NotBlank(message="Last name is required.")
    @Size(max=15)
    private String lname;

    @NotBlank(message="Email is required.")
    @Email(message="Email is not valid.")
    private String username;

    @NotBlank(message="Password is required.")
    private String password;

    private boolean isActive;

    private String role;

    @ManyToMany
    @JsonIgnore
    private List<Garden> gardens = new ArrayList<>();

    public User() {
    }


    public User(String fname, String lname, String username, String password) {
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.gardens = gardens;
        this.role = "ROLE_USER";
        this.isActive = true;
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

    public List<Garden> getGardens() {
        return gardens;
    }

    public void setGardens(List<Garden> gardens) {
        this.gardens = gardens;
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

    public void setRole(String role) {
        this.role = role;
    }

}

