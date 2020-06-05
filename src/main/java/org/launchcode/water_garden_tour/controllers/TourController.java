package org.launchcode.water_garden_tour.controllers;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.launchcode.water_garden_tour.models.User;
import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.data.UserRepository;
import org.launchcode.water_garden_tour.models.garden.Feature;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class TourController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private UserRepository userRepository;

    List<Garden> tourGardens = new ArrayList<>();
    List<User> tourUsers = new ArrayList<>();

    @GetMapping("/gardens/tour/{gardenId}")
    public String addTourGarden(Model model, @PathVariable("gardenId") Integer gardenId) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> currentUser = userRepository.findByUsername(username);
        User tourUser = currentUser.get();
        tourUsers.add(tourUser);

        Optional<Garden> chosenGarden = gardenRepository.findById(gardenId);
        if (chosenGarden.isPresent()) {
            Garden tourGarden = chosenGarden.get();
            if (!tourGardens.contains(tourGarden)) {
                tourGardens.add(tourGarden);
            }
        }

        tourUser.setGardens(tourGardens);
        userRepository.save(tourUser);

        List<Feature> selectedFeatures = new ArrayList<>();

        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("tourGardens", tourGardens);
        model.addAttribute("tourUsers", tourUsers);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("selectedFeatures", selectedFeatures);
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("searchTerm", "");
        model.addAttribute("title", "My Water Garden Tour");
        return "/gardens/list";
    }

    @GetMapping("/gardens/tour")
    public String listGardenTour(Model model) {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> currentUser = userRepository.findByUsername(username);
        User tourUser = currentUser.get();
        tourGardens = tourUser.getGardens();

        model.addAttribute("tourGardens", tourGardens);
        //model.addAttribute("tourUsers", tourUsers);
        model.addAttribute("title", "My Water Garden Tour");

        return "gardens/tour";
    }

    @GetMapping("/gardens/tour/delete/{gardenId}")
    public String deleteGarden(Model model, @PathVariable("gardenId") Integer gardenId) {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        Optional<User> currentUser = userRepository.findByUsername(username);
        User tourUser = currentUser.get();
        tourUsers.add(tourUser);

        Optional<Garden> chosenGarden = gardenRepository.findById(gardenId);
        if (chosenGarden.isPresent()) {
            Garden tourGarden = chosenGarden.get();
                tourGardens.remove(tourGarden);
        }

        tourUser.setGardens(tourGardens);
        userRepository.save(tourUser);

        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("tourGardens", tourGardens);
        model.addAttribute("tourUsers", tourUsers);
        model.addAttribute("users", userRepository.findAll());

        return "/gardens/tour";
    }

}
