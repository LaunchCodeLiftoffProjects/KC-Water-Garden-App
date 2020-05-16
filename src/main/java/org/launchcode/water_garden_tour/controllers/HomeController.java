package org.launchcode.water_garden_tour.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title","Kansas City Water Garden Tours");

        return "index";
    }


    @GetMapping("/home")
    @ResponseBody
    public String home() {
        return "Welcome";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "Welcome User";
    }

    @GetMapping("/admin")
    public String renderAdminPage() {
        return "admin";
    }

}
