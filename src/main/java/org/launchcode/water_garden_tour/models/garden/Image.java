package org.launchcode.water_garden_tour.models.garden;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class Image extends AbstractEntity {

    @Lob
    private byte[] image;

    public Image(byte[] image) {
        this.image = image;
    }

    public Image() {}

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
