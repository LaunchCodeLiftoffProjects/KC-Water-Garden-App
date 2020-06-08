package org.launchcode.water_garden_tour.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.launchcode.water_garden_tour.models.data.FeatureRepository;
import org.launchcode.water_garden_tour.models.data.GardenRepository;
import org.launchcode.water_garden_tour.models.data.OwnerRepository;
import org.launchcode.water_garden_tour.models.garden.Feature;
import org.launchcode.water_garden_tour.models.garden.Garden;
import org.launchcode.water_garden_tour.models.garden.GardenData;
import org.launchcode.water_garden_tour.models.garden.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("gardens/list")
    public String listGarden(Model model) {
        List<Feature> selectedFeatures = new ArrayList<>();

        model.addAttribute("selectedFeatures", selectedFeatures);
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("title", "Garden List");
        model.addAttribute("searchTerm", "");

        return "gardens/list";
    }

    @GetMapping("gardens/search")
    public String renderGardenSearch(Model model, @RequestParam(required=false, value="searchTerm") String searchTerm, @RequestParam(required = false, value="featureIds") Integer[] featureIds) {
        Iterable<Garden> gardens;
        List<Feature> allFeatures = featureRepository.findAll();
        List<Feature> selectedFeatures = new ArrayList<>();

        if (featureIds != null) {
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

    @GetMapping("/garden/list/{gardenId}")
    public void showImage(@PathVariable ("gardenId") Integer gardenId,
                               HttpServletResponse response) throws IOException {

        Optional<Garden> garden = gardenRepository.findById(gardenId);

        response.setContentType("image/jpeg");

        InputStream is = new ByteArrayInputStream(garden.get().getImage());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("/gardens/feature/{featureId}")
    public String renderGardenViewByFeature(Model model, @PathVariable int featureId) {
        List<Garden> gardensWithFeature = new ArrayList<>();
        List<Feature> curFeatures = new ArrayList<>();

        Feature featureForView = GardenData.getFeatureFromId(featureId, featureRepository.findAll());


        for (Garden garden : gardenRepository.findAll()) {
            curFeatures = garden.getFeatures();
            if (curFeatures.contains(featureForView)) {
                if (!gardensWithFeature.contains(garden)) {
                    gardensWithFeature.add(garden);
                }
            }
        }


        String title = featureForView + "View";


        model.addAttribute("gardens", gardensWithFeature);
        model.addAttribute("feature", featureForView);
        model.addAttribute("title", "Feature View");

        return "gardens/feature-view";
    }

    @GetMapping("/view/{gardenId}")
    public String viewGarden(Model model, @PathVariable int gardenId) {
        Optional<Garden> foundGarden = gardenRepository.findById(gardenId);
        Garden garden = foundGarden.get();
        model.addAttribute("garden", garden);
        model.addAttribute("title", garden.getName());
        return "gardens/view";
    }

}