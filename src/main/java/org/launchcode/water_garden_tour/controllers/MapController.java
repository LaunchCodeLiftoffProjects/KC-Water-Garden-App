package org.launchcode.water_garden_tour.controllers;

import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {

    @Autowired
    GardenRepository gardenRepository;

    @RequestMapping("map")
    public String map(Model model) {
        model.addAttribute("gardens", gardenRepository.findAll());
        return "map";
    }

}