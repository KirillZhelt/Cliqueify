package dev.kirillzhelt.cliqueify.controller;

import dev.kirillzhelt.cliqueify.dto.UserDTO;
import dev.kirillzhelt.cliqueify.model.User;
import dev.kirillzhelt.cliqueify.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;
    private ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid UserDTO user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Passwords do not match");
            return "registration";
        }

        User userDb = this.modelMapper.map(user, User.class);

        if (!this.userService.saveUser(userDb)) {
            model.addAttribute("usernameError", "Such username already exists");
            return "registration";
        }

        return "redirect:/";
    }

}
