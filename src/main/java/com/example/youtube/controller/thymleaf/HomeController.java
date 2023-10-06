
package com.example.youtube.controller.thymleaf;

import com.example.youtube.Service.AuthService;
import com.example.youtube.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    private final AuthService authService;

    public HomeController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/home")

    public String gohome(Model model){
        model.addAttribute("userLogin",  authService.findByName(authService.getCurrentUser()));

        return "index";
    }
    @GetMapping("login")
    public String login(){
        return "login";}}
