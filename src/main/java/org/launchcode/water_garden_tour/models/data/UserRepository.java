package org.launchcode.water_garden_tour.models.data;

import org.launchcode.water_garden_tour.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
<<<<<<< HEAD

@Repository

=======
import javax.transaction.Transactional;

@Repository
@Transactional
>>>>>>> 0652212... Created a custom login form, login controller & updated Spring Security settings to use our login form. Added the new User fields to UserDetailsImplementation. Updated security configuration to properly implement password encoder using DaoAuthenticationProvider.
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
