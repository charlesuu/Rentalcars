package com.example.rentalcars.controller;

import com.example.rentalcars.domain.user.User;
import com.example.rentalcars.domain.user.UserRepository;
import com.example.rentalcars.dto.user.response.UserResponse;
import com.example.rentalcars.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    UserService userService;
    UserRepository userRepository;

    public IndexController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping("/user/save")
    public String userSave() {
        return "user-save";
    }

    @GetMapping("/user/update/{id}")
    public String userUpdate(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        model.addAttribute("user", new UserResponse(id, user));

        return "user-update";
    }


    @GetMapping("/car/save")
    public String carSave() {
        return "car-save";
    }

    @GetMapping("/car/rental/{id}")
    public String carLoan(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        model.addAttribute("user", new UserResponse(id, user));

        return "car-rental";
    }

}
