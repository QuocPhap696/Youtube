package com.example.youtube.controller.thymleaf;

import com.example.youtube.Service.UserService;
import com.example.youtube.Service.video.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChannelOtherUser {
    private final VideoService videoService;
    private final UserService userService;

    public ChannelOtherUser(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
    }

    @GetMapping("/channelOtherUser")
    public String goChannel(@RequestParam int userId, Model model){
        model.addAttribute("videos",videoService.findVideosByUserId(userId));
        model.addAttribute("user",userService.findById(userId));
        return "channelOtherUser";
    }
}
