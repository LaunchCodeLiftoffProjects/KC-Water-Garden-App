package org.launchcode.water_garden_tour.models.garden;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Feature extends AbstractEntity {

    private String name;

    @ManyToMany(mappedBy = "features")
    private List<Garden> gardens = new ArrayList<>();

    public Feature() {}

    public Feature(String name, List<Garden> gardens) {
        this.name = name;
        this.gardens = gardens;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Garden> getGardens() {
        return gardens;
    }

    public void setGardens(List<Garden> gardens) {
        this.gardens = gardens;
    }
}
