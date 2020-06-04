package org.launchcode.water_garden_tour.controllers;

import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private GardenRepository gardenRepository;

    @GetMapping("")
    public String index(Model model) {
        List<Garden> featuredGardens = new ArrayList<>();
        List<Garden> allGardens = gardenRepository.findAll();

        while (featuredGardens.size() < 3) {
            Garden curGarden;
            double randomNumberDbl = Math.random() * allGardens.size();
            int randomNumber = (int) Math.floor(randomNumberDbl);
            curGarden = allGardens.get(randomNumber);
            if (!featuredGardens.contains(curGarden)) {
                featuredGardens.add(curGarden);
            }
        }

        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("gardens", featuredGardens);
        model.addAttribute("title", "Kansas City Water Garden Tours");

        return "index";
    }
}
