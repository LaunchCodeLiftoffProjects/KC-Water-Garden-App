package org.launchcode.water_garden_tour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
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
    @ResponseBody
    public String hello() {
        return "Welcome Admin";
    }

}
