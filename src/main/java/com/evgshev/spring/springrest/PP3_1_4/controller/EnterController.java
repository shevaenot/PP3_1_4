package com.evgshev.spring.springrest.PP3_1_4.controller;

import com.evgshev.spring.springrest.PP3_1_4.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnterController {

    @GetMapping(value = "/enter")
    public String pageForEnter(Model model) {
        model.addAttribute("logins", new User());
        return "enter";
    }

}
