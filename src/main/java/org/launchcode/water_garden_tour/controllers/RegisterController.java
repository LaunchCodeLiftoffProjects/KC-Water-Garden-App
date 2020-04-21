package org.launchcode.water_garden_tour.controllers;

import org.springframework.ui.Model;
import org.launchcode.water_garden_tour.models.User;
import org.launchcode.water_garden_tour.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    public static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute(new User());
        model.addAttribute("title", "Registration");
        return "register";

    }


    /*@PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid User newUser, Errors errors, Model model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Registration");
            model.addAttribute("user", newUser);
        }


        Optional<User> existingUser = userRepository.findByUsername(newUser.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that email already exists");
            model.addAttribute("title", "Registration");
            return "register";
        }

        String password = newUser.getPassword();
        String verify = newUser.getVerifyPassword();
        if (!password.equals(verify)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Registration");
            return "register";
        }

        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/";


    }

    }*/
}

