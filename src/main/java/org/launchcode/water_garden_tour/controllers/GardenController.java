package org.launchcode.water_garden_tour.controllers;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.data.OwnerRepository;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class GardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    //****Garden controllers

    @GetMapping("/gardens/list")
    public String listGarden(Model model) {

        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden List");

        return "gardens/list";
    }

    @GetMapping("/garden/list/{gardenId}")
    public void showImage(@PathVariable ("gardenId") Integer gardenId,
                               HttpServletResponse response) throws IOException {

        Optional<Garden> garden = gardenRepository.findById(gardenId);

        response.setContentType("image/jpeg");

        InputStream is = new ByteArrayInputStream(garden.get().getImage());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("view/{gardenId}")
    public String viewGarden(Model model, @PathVariable int gardenId) {
        Optional<Garden> foundGarden = gardenRepository.findById(gardenId);
        Garden garden = foundGarden.get();
        model.addAttribute("garden", garden);
        return "gardens/view";
    }

}
