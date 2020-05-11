package org.launchcode.water_garden_tour.controllers;

import org.launchcode.water_garden_tour.models.dto.RegisterDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.launchcode.water_garden_tour.models.User;
import org.launchcode.water_garden_tour.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @Autowired
    PasswordEncoder passwordEncoder;

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
        model.addAttribute(new RegisterDTO());
        model.addAttribute("title", "Registration");
        return "register";

    }


    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterDTO registerDTO, BindingResult result, Model model, Errors errors, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("title", "Registration");
            return "register";
        }

        Optional<User> existingUser = userRepository.findByUsername(registerDTO.getUsername());

        if (existingUser.isPresent()) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that email already exists");
            model.addAttribute("title", "Registration");
            return "register";
        }

        String password = registerDTO.getPassword();
        String verify = registerDTO.getVerifyPassword();
        if (!password.equals(verify)) {
            result.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Registration");
            return "register";
        }

        User newUser = new User(registerDTO.getFname(), registerDTO.getLname(), registerDTO.getUsername(), passwordEncoder.encode(registerDTO.getPassword()));

        userRepository.save(newUser);

        setUserInSession(request.getSession(), newUser);

        return "login";

    }

}

