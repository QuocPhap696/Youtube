package com.example.youtube.controller.thymleaf;

import com.example.youtube.Service.AuthService;
import com.example.youtube.model.Video;
import com.example.youtube.repository.VideoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/singlePages")
public class    SinglePageController{
    private final VideoRepository videoRepository;
    private final AuthService authService;



    public SinglePageController(VideoRepository videoRepository, AuthService authService) {
        this.videoRepository = videoRepository;
        this.authService = authService;
    }

    @GetMapping("/show")
    public String showSinglePages(@RequestParam int id, Model model){
        Video video=videoRepository.findById(id);
       model.addAttribute("video",video );

        return "single_page";
    }
}
