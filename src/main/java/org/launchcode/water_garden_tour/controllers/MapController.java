package org.launchcode.water_garden_tour.controllers;

import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class MapController {

    @Autowired
    GardenRepository gardenRepository;

    @RequestMapping("map")
    public String map(Model model, @RequestParam(required=false, value="gardenId") Integer gardenId) {

        if (gardenId == null) {

            model.addAttribute("gardens", gardenRepository.findAll());
            model.addAttribute("title", "Garden Map View");

        } else {

            List<Garden> singleGarden = new ArrayList<>();
            singleGarden.add(gardenRepository.getOne(gardenId));

            model.addAttribute("gardens", singleGarden);
            model.addAttribute("title", "Individual Garden Map View");

        }

        return "map";
    }

}