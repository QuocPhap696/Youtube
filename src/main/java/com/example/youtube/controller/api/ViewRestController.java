package com.example.youtube.controller.api;
import com.example.youtube.Service.video.VideoService;
import com.example.youtube.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/view")
public class ViewRestController {

    private final VideoService videoService;


    public ViewRestController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<?> getViewCount(@RequestParam("idVideo") int idVideo , Authentication authentication) {

        Video video = videoService.getSingleUserView(idVideo, authentication.getName());
        return ResponseEntity.ok(video);

    }
}