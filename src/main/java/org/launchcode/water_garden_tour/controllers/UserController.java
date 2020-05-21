package org.launchcode.water_garden_tour.controllers;


import org.launchcode.water_garden_tour.models.User;
import org.launchcode.water_garden_tour.models.data.UserRepository;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.launchcode.water_garden_tour.models.garden.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/list")
    public String displaySearchPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("title", "Users");
        return "users/list";
    }


    @GetMapping("/users/search")
    public String displaySearchResults(Model model, @RequestParam(required = false, value = "searchTerm") String searchTerm) {
        Iterable<User> users;
        List<User> allUsers = userRepository.findAll();
        List<User> selectedUsers = new ArrayList<>();

        if (searchTerm.isEmpty()) {
            model.addAttribute("results", userRepository.findAll());
            model.addAttribute("searchTerm", "All Users");
        } else {
            for (User user : allUsers) {

                if (user.getFname().toLowerCase().equals(searchTerm.toLowerCase())) {
                    selectedUsers.add(user);
                }

                if (user.getLname().toLowerCase().equals(searchTerm.toLowerCase())) {
                    selectedUsers.add(user);
                }
            }
            model.addAttribute("results", selectedUsers);
            model.addAttribute("searchTerm", searchTerm);
        }
        model.addAttribute("title", "User Search");
        return "users/search";
    }

    @GetMapping("/users/update")
    public String renderUpdateUser(Model model, @RequestParam(value = "userId") int userId) {

        Optional <User> optUser = userRepository.findById(userId);
        System.out.println(userId);
        System.out.println(optUser);
        if (optUser.isPresent()) {
            User userToUpdate = (User) optUser.get();
            model.addAttribute("user", userToUpdate);
        }

        model.addAttribute("title", "Update User");

        return "users/update";
    }

    @PostMapping("/users/update")
    public String updateUser(Model model,
                              int userId,
                              String password,
                              String username,
                              String fname,
                              String lname,
                              String role) {

        Optional<User> optUser = userRepository.findById(userId);
        System.out.println(userId);
        System.out.println(optUser);
        User userToUpdate = (User) optUser.get();

        userToUpdate.setFname(fname);
        userToUpdate.setLname(lname);
        userToUpdate.setUsername(username);
        userToUpdate.setRole(role);
        userToUpdate.setPassword(password);

        userRepository.save(userToUpdate);

            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("title", "User List");
            return "users/list";
        }

    @PostMapping("/users/delete")
    public String deleteOwner(Model model, @RequestParam int userId) {

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User userToDelete = (User) optUser.get();

            userRepository.delete(userToDelete);
        }

        //attributes for return to admin-list
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("title", "User List");

        return "users/list";

    }

}



        /*if (featureIds != null) {
            gardens = GardenData.gardenSearchFeaturesFiltered(searchTerm, featureIds, allFeatures, gardenRepository.findAll());

            for (int id : featureIds) {
                selectedFeatures.add(GardenData.getFeatureFromId(id, allFeatures));
            }

        } else {
            gardens = GardenData.gardenSearchAllFeatures(searchTerm, allFeatures, gardenRepository.findAll());

        }

        model.addAttribute("gardens", gardens);
        model.addAttribute("selectedFeatures", selectedFeatures);
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("title", "Garden Search");

        return "gardens/list";
    }



    @PostMapping("/users/search")
    public String processSearch(Model model, @RequestParam String searchTerm) {

        List<User> users = new ArrayList<>();

        for (User user : users) {

            if (user.getFname().toLowerCase().equals(searchTerm.toLowerCase())) {

                results.add(user);

            }
            model.addAttribute("results", results);
        }
        return "users/results";
    }

    @GetMapping("/users/results")
    public String displaySearchResults(Model model) {

        model.addAttribute("title", "Search Results");
        model.addAttribute("results", results);
        return "users/search";
    }*/




