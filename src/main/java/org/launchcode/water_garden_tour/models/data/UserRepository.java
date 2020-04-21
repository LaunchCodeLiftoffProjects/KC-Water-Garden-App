package org.launchcode.water_garden_tour.models.data;

import org.launchcode.water_garden_tour.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
