package com.example.youtube.controller.thymleaf;


import com.example.youtube.Service.video.VideoService;
import com.example.youtube.model.Video;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TopTrendingController {
    private final VideoService videoService;

    public TopTrendingController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/topTrending")
    public String showTopTrendingPage(Model model) {
        List<Video> topTrendingVideos = videoService.getTopTrendingVideosLast30Days();
        model.addAttribute("topTrendingVideos", topTrendingVideos);
        return "topTrending";
    }
}