package org.launchcode.water_garden_tour.models.garden;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Owner extends AbstractEntity{

    @OneToMany(mappedBy = "owner")
    private final List<Garden> gardens = new ArrayList<>();

    @NotBlank
    @Size(max=100)
    private String name;

    @NotBlank
    @Size(max=50)
    private String email;

    @NotBlank
    @Size(max=14)
    private String phoneNum;

    public Owner(String name, String email, String phoneNum) throws IOException {
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
    }

    public Owner() {}



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Garden> getGardens() {
        return gardens;
    }

    @Override
    public String toString() {
        return name;
    }
}
