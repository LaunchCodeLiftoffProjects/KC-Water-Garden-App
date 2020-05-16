package org.launchcode.water_garden_tour.models.garden;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Image extends AbstractEntity {

    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn
    private Garden garden;

    public Image(byte[] image, Garden garden) {
        this.image = image;
        this.garden = garden;
    }

    public Image() {}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setGarden(Garden garden) {
        this.garden = garden; }

    public Garden getGarden() { return garden; }
}
