package org.launchcode.water_garden_tour.models.garden;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Garden extends AbstractEntity {

    @NotBlank(message = "Name is required.")
    @Size(max = 100, message = "Exceeds maximum space allowed.")
    private String name;

    @NotBlank(message="Garden must have an address.")
    private String address;

    @NotBlank(message = "Latitude is required.")
    private String latitude;

    @NotBlank(message = "Longitude is required.")
    private String longitude;

    @NotBlank(message="Description is required.")
    @Size(max=1000, message="Description exceeds maximum number of characters")
    private String description;

    private String image;

    @ManyToOne
    @JoinColumn
    private Owner owner;

    @ManyToMany
    private List<Feature> features = new ArrayList<>();

    public Garden(String name, String address, String latitude, String longitude, String description, String image, Owner owner, List<Feature> features) throws IOException {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.image = image;
        this.owner = owner;
        this.features = features;
    }

    public Garden() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return name;
    }

}
