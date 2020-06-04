package org.launchcode.water_garden_tour.controllers;


import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.garden.Feature;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    List<Garden> tourGardens = new ArrayList<>();

    @GetMapping("/gardens/tour/{gardenId}")
    public String addTourGarden(Model model, @PathVariable("gardenId") Integer gardenId) {

        Optional<Garden> chosenGarden = gardenRepository.findById(gardenId);
        if (chosenGarden.isPresent()) {
            Garden tourGarden = chosenGarden.get();
            if (!tourGardens.contains(tourGarden)) {
                tourGardens.add(tourGarden);
            }
        }

        List<Feature> selectedFeatures = new ArrayList<>();

        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("tourGardens", tourGardens);
        model.addAttribute("selectedFeatures", selectedFeatures);
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("searchTerm", "");
        model.addAttribute("title", "My Water Garden Tour");
        return "/gardens/list";
    }

    @GetMapping("/gardens/tour")
    public String listGardenTour(Model model) {

        model.addAttribute("tourGardens", tourGardens);
        model.addAttribute("title", "My Water Garden Tour");

        return "gardens/tour";
    }

    @GetMapping("/gardens/tour/delete/{gardenId}")
    public String deleteGarden(Model model, @PathVariable("gardenId") Integer gardenId) {

        Optional<Garden> optGarden = gardenRepository.findById(gardenId);
        if (optGarden.isPresent()) {
            Garden garden = optGarden.get();
            tourGardens.remove(garden);
        }

        model.addAttribute("tourGardens", tourGardens);
        model.addAttribute("title", "My Water Gardens Tour");

        return "gardens/tour";
    }

}
