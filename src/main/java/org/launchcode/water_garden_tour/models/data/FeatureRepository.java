package org.launchcode.water_garden_tour.models.data;

import org.launchcode.water_garden_tour.models.garden.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface FeatureRepository extends JpaRepository<Feature, Integer> {

}
