package com.example.youtube.controller.api;

import com.example.youtube.Service.AuthService;
import com.example.youtube.Service.like.LikeService;

import com.example.youtube.Service.video.VideoService;

import com.example.youtube.model.Likes;
import com.example.youtube.model.User;
import com.example.youtube.model.Video;
import com.example.youtube.repository.VideoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/likes")
public class LikeResController {
    private final LikeService likeService;
    private final VideoService videoService;
    private final AuthService authService;
    private final VideoRepository videoRepository;

    public LikeResController(LikeService likeService, VideoService videoService, AuthService authService, VideoRepository videoRepository) {
        this.likeService = likeService;
        this.videoService = videoService;

        this.authService = authService;
        this.videoRepository = videoRepository;
    }

    @PostMapping ("/create")
    public ResponseEntity<?> create(@RequestBody Likes request){

        User user=  authService.findByName(authService.getCurrentUser());
        if(user==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("login first");
        }else {
            Video video =request.getVideo();
            Likes likes= likeService.findByUserAndVideo(user,video);
            if(likes==null){
                likeService.create(new Likes(request.getLikeStatus(),user,video));
            }else if(likes.getLikeStatus()==request.getLikeStatus()){
                likeService.deleteLike(likes);
            }else {
                likes.setLikeStatus(request.getLikeStatus());
                likeService.create(likes);
            }
        }
        int countLike=likeService.countLike(request.getVideo());
        int countDisLike=likeService.countDisLike(request.getVideo());
        Map<String,Object> data=new HashMap<>();
        data.put("countLike",countLike);
        data.put("countDisLike",countDisLike);
        return ResponseEntity.ok(data);
    }

    @PostMapping ("/getLike")
    public ResponseEntity<?> getLike(@RequestBody Likes request){

        User user=  authService.findByName(authService.getCurrentUser());
        if(user==null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("login first");
        }else {
            Video video =request.getVideo();
            Likes likes= likeService.findByUserAndVideo(user,video);
            int countLike=likeService.countLike(request.getVideo());
            int countDisLike =likeService.countDisLike(request.getVideo());
            Map<String,Object> data= new HashMap<>();
            data.put("likeStatus",likes);
            data.put("countLike",countLike);
            data.put("countDisLike",countDisLike);
            return ResponseEntity.ok(data);
        }
    }
}
