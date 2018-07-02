package com.github.sebastiancegielka.controller;

import com.github.sebastiancegielka.dto.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping(value = "/user")
public class RegistrationController {

    @GetMapping("/registration")
    public String showRegistrationForm(WebRequest request, Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }
}
