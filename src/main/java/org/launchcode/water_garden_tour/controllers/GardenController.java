package org.launchcode.water_garden_tour.controllers;

import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.data.OwnerRepository;
import org.launchcode.water_garden_tour.models.garden.Feature;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.launchcode.water_garden_tour.models.garden.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("")
public class GardenController {

    @Autowired
    private GardenRepository gardenRepository;

    @Autowired
    private FeatureRepository featureRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    //****Garden controllers

    @GetMapping("/gardens/add")
    public String renderAddGarden(Model model) {
        model.addAttribute("title", "Add Garden");
        model.addAttribute("owners", ownerRepository.findAll());
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute(new Garden());
        return "gardens/add";
    }

    @PostMapping("/gardens/add")
    public String addGarden(@ModelAttribute @Valid Garden newGarden,
                            Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "/gardens/add";
        }


        gardenRepository.save(newGarden);
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden List");

        return "/gardens/list";
    }

    @GetMapping("/gardens/list")
    public String listGarden(Model model) {
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden List");

        return "gardens/list";
    }


    //****Feature Controllers

    @GetMapping("/features/add")
    public String renderAddFeature(Model model) {
        model.addAttribute("title", "Add Feature");
        model.addAttribute(new Feature());
        return "features/add";
    }

    @PostMapping("/features/add")
    public String addFeature(@ModelAttribute @Valid Feature newFeature,
                            Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "/features/add";
        }

        featureRepository.save(newFeature);

        return "redirect:";
    }

    @GetMapping("/features")
    public String listFeature(Model model) {
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("title", "Feature List");

        return "/features/list";
    }


    //****Owner Controllers

    @GetMapping("/owners/add")
    public String renderAddOwner(Model model) {
        model.addAttribute("title", "Add Owner");
        model.addAttribute(new Owner());
        return "owners/add";
    }

    @PostMapping("/owners/add")
    public String addGarden(@ModelAttribute @Valid Owner newOwner,
                            Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "owners/add";
        }

        ownerRepository.save(newOwner);

        return "redirect:";
    }

    @GetMapping("/owners")
    public String listOwner(Model model) {
        model.addAttribute("title", "Owners List");
        model.addAttribute("owners", ownerRepository.findAll());
        return "owners/list";
    }

    @GetMapping("view/{gardenId}")
    public String viewGarden(Model model, @PathVariable int gardenId) {
        Optional<Garden> foundGarden = gardenRepository.findById(gardenId);
        Garden garden = foundGarden.get();
        model.addAttribute("garden", garden);
        return "gardens/view";
    }

}
