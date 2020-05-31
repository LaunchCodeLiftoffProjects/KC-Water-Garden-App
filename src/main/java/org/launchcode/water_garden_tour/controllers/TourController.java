package org.launchcode.water_garden_tour.controllers;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
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
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("tourGardens", tourGardens);
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
