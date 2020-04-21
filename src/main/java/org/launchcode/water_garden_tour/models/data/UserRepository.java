package org.launchcode.water_garden_tour.models.data;

import org.launchcode.water_garden_tour.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import javax.transaction.Transactional;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
<<<<<<< HEAD
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
=======
public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmail(String email);

>>>>>>> a0890b7... registration page is displaying
}
