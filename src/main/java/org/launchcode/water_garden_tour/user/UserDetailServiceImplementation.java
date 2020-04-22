package org.launchcode.water_garden_tour.user;

import org.launchcode.water_garden_tour.models.User;
import org.launchcode.water_garden_tour.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImplementation implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(username);
        // the below exception set-up does not work. Getting a no value present error when not in database
        // may be due to the user object is not null, but is empty.
        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + " not found.");
        }
        // passes the user object to the new function/constructor of UserDetailsImplementation
        // and gets the value.
        return user.map(UserDetailsImplementation::new).get();

    }
}
