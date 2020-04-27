package org.launchcode.water_garden_tour.models.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterDTO {

    @NotBlank
    private String fname;

    @NotBlank
    private String lname;

    @NotBlank
    @Size(min=5, max=20, message="Invalid username")
    private String username;

    @NotBlank
    @Size(min=5, max=20, message="Invalid password")
    private String password;

    @NotBlank
    @Size(min=5, max=20, message="Invalid password")
    private String verifyPassword;

    public RegisterDTO() {}

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
}
