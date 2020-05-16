package org.launchcode.water_garden_tour.models.data;

import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GardenRepository extends JpaRepository<Garden, Integer> {

    Garden findByName(String name);

}
