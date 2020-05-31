package org.launchcode.water_garden_tour.controllers;

import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private FeatureRepository featureRepository;

    @GetMapping("")
    public String index(Model model) {

        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("title","Kansas City Water Garden Tours");

        return "index";
    }

}
