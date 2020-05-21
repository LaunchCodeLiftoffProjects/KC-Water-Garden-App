package org.launchcode.water_garden_tour.controllers;


import org.apache.tomcat.util.http.fileupload.IOUtils;
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
    public String addGarden(@ModelAttribute @Valid Garden newGarden, @RequestParam("file") MultipartFile file,
                            Errors errors, Model model) throws IOException {

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


    @GetMapping("/garden/list/{gardenId}")
    public void showImage(@PathVariable ("gardenId") Integer gardenId,
                               HttpServletResponse response) throws IOException {

        Optional<Garden> garden = gardenRepository.findById(gardenId);

        response.setContentType("image/jpeg");

        InputStream is = new ByteArrayInputStream(garden.get().getImage());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("/gardens/admin-list")
    public String listAdminGarden(Model model) {
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden ADMIN List");

        return "gardens/admin-list";
    }

    @PostMapping("/gardens/delete")
    public String deleteGarden(Model model, @RequestParam int gardenId) {

        Optional optGarden = gardenRepository.findById(gardenId);
        if (optGarden.isPresent()) {
            Garden garden = (Garden) optGarden.get();

            gardenRepository.delete(garden);
        }

        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden ADMIN List");

        return "gardens/admin-list";
    }

    @GetMapping("/gardens/update")
    public String renderUpdateGarden(Model model, @RequestParam(value = "gardenId") int gardenId) {

        Optional optGarden = gardenRepository.findById(gardenId);
        if (optGarden.isPresent()) {
            Garden gardenToUpdate = (Garden) optGarden.get();
            model.addAttribute("garden", gardenToUpdate);

            List<Feature> gardenFeatures = gardenToUpdate.getFeatures();

            Owner gardenOwner = gardenToUpdate.getOwner();
            int ownerId = gardenOwner.getId();

            model.addAttribute("gardenOwner", gardenOwner);
            model.addAttribute("gardenFeatures", gardenFeatures);
        }
        model.addAttribute("title", "Update Garden");
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("owners", ownerRepository.findAll());

        return "gardens/update";
    }

    @PostMapping("/gardens/update")
    public String updateGarden(Model model,
                               int gardenId,
                               @RequestParam("file") MultipartFile file,
                               String name,
                               String address,
                               String latitude,
                               String longitude,
                               String description,
                               int ownerId,
                               Integer[] featureIds) throws IOException {

        Optional<Garden> optGarden = gardenRepository.findById(gardenId);
        Garden gardenToUpdate = (Garden) optGarden.get();

        Optional<Owner> optOwner = ownerRepository.findById(ownerId);
        Owner owner = (Owner) optOwner.get();

        List<Feature> features = new ArrayList<>();
        for (Integer id : featureIds) {

            Optional<Feature> optFeature = featureRepository.findById(id);
            Feature featureToAdd = (Feature) optFeature.get();

            features.add(featureToAdd);
        }


        gardenToUpdate.setName(name);
        gardenToUpdate.setAddress(address);
        gardenToUpdate.setLatitude(latitude);
        gardenToUpdate.setLongitude(longitude);
        gardenToUpdate.setDescription(description);
        if(!file.isEmpty()) {
            gardenToUpdate.setImage(file.getBytes());
        }
        gardenToUpdate.setOwner(owner);
        gardenToUpdate.setFeatures(features);

        gardenRepository.save(gardenToUpdate);

        //attributes for return to admin-list
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden ADMIN List");

        return "gardens/admin-list";
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

    @PostMapping("/features/delete")
    public String deleteFeature(Model model, @RequestParam int featureId) {

        Optional optFeature = featureRepository.findById(featureId);
        if (optFeature.isPresent()) {
            Feature featureToDelete = (Feature) optFeature.get();

            List<Garden> gardens = gardenRepository.findAll();
            for (Garden garden : gardens) {
                List<Feature> curGardenFeatures = garden.getFeatures();
                if (curGardenFeatures.contains(featureToDelete)) {
                    curGardenFeatures.remove(featureToDelete);

                    garden.setFeatures(curGardenFeatures);
                    gardenRepository.save(garden);
                }
            }

            featureRepository.delete(featureToDelete);
        }

        //attributes for return to admin-list
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden ADMIN List");

        return "gardens/admin-list";
    }

    @GetMapping("/features/update")
    public String renderUpdateFeature(Model model, @RequestParam(value = "featureId") int featureId) {

        Optional optFeature = featureRepository.findById(featureId);
        if (optFeature.isPresent()) {
            Feature featureToUpdate = (Feature) optFeature.get();
            model.addAttribute("feature", featureToUpdate);
        }

        model.addAttribute("title", "Update Feature");

        return "features/update";
    }

    @PostMapping("/features/update")
    public String updateFeature(Model model,
                              int featureId,
                              String name) {

        Optional<Feature> optFeature = featureRepository.findById(featureId);
        Feature featureToUpdate = (Feature) optFeature.get();

        featureToUpdate.setName(name);

        featureRepository.save(featureToUpdate);

        //attributes for return to owner list
        model.addAttribute("features", featureRepository.findAll());
        model.addAttribute("title", "Feature List");

        return "features/list";
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

    @PostMapping("/owners/delete")
    public String deleteOwner(Model model, @RequestParam int ownerId) {

        Optional optOwner = ownerRepository.findById(ownerId);
        if (optOwner.isPresent()) {
            Owner ownerToDelete = (Owner) optOwner.get();

            ownerRepository.delete(ownerToDelete);
        }

        //attributes for return to admin-list
        model.addAttribute("gardens", gardenRepository.findAll());
        model.addAttribute("title", "Garden ADMIN List");

        return "gardens/admin-list";

    }

    @GetMapping("/owners")
    public String listOwner(Model model) {
        model.addAttribute("title", "Owners List");
        model.addAttribute("owners", ownerRepository.findAll());

        //delete button only for owners not attached to gardens
        List<Garden> gardens = gardenRepository.findAll();
        List<Owner> ownersWithGardens = new ArrayList<>();

        for (Garden garden : gardens) {
            Optional optGardenOwner = ownerRepository.findById(garden.getOwner().getId());
            if (optGardenOwner.isPresent()) {
                Owner curOwner = (Owner) optGardenOwner.get();

                ownersWithGardens.add(curOwner);
            }

            model.addAttribute("ownersWithGardens", ownersWithGardens);

        }

        return "owners/list";
    }

    @GetMapping("/owners/update")
    public String renderUpdateOwner(Model model, @RequestParam(value = "ownerId") int ownerId) {

        Optional optOwner = ownerRepository.findById(ownerId);
        if (optOwner.isPresent()) {
            Owner ownerToUpdate = (Owner) optOwner.get();
            model.addAttribute("owner", ownerToUpdate);
        }

        model.addAttribute("title", "Update Owner");

        return "owners/update";
    }

    @PostMapping("/owners/update")
    public String updateOwner(Model model,
                               int ownerId,
                               String name,
                               String email,
                               String phoneNum) {

        Optional<Owner> optOwner = ownerRepository.findById(ownerId);
        Owner ownerToUpdate = (Owner) optOwner.get();

        ownerToUpdate.setName(name);
        ownerToUpdate.setEmail(email);
        ownerToUpdate.setPhoneNum(phoneNum);

        ownerRepository.save(ownerToUpdate);

        //attributes for return to owner list
        model.addAttribute("owners", ownerRepository.findAll());
        model.addAttribute("title", "Owner List");

        //delete button only for owners not attached to gardens
        List<Garden> gardens = gardenRepository.findAll();
        List<Owner> ownersWithGardens = new ArrayList<>();

        for (Garden garden : gardens) {
            Optional optGardenOwner = ownerRepository.findById(garden.getOwner().getId());
            if (optGardenOwner.isPresent()) {
                Owner curOwner = (Owner) optGardenOwner.get();

                ownersWithGardens.add(curOwner);
            }

            model.addAttribute("ownersWithGardens", ownersWithGardens);

        }

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
