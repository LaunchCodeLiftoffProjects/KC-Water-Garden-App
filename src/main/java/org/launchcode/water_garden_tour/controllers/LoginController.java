package org.launchcode.water_garden_tour.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method = {RequestMethod.GET, RequestMethod.POST})
    String login() {
        return "login";
    }
}
