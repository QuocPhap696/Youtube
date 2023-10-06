package com.example.youtube.controller.api;

import com.example.youtube.Service.video.VideoService;
import com.example.youtube.model.Video;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/topTrending")
public class TopTrendingRestController {




    private final VideoService videoService;

    public TopTrendingRestController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getTopTrendingVideosLast30Days() {
        List<Video> topTrendingVideos = videoService.getTopTrendingVideosLast30Days();
        return new ResponseEntity<>(topTrendingVideos, HttpStatus.OK);
    }
}