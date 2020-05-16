package org.launchcode.water_garden_tour.models.data;
import org.launchcode.water_garden_tour.models.garden.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Integer> {

}
