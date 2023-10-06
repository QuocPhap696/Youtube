//
//package com.example.youtube.controller.thymleaf;
//
//import com.example.youtube.Service.video.VideoService;
//
//
//import com.example.youtube.Service.video.VideoServiceImpl;
//import com.example.youtube.model.Video;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.List;
//
//@Controller
//public class ChanelController {
//    private final VideoService videoService;
//    private final VideoServiceImpl videoServiceImpl;
//
//    public ChanelController(VideoService videoService, VideoServiceImpl videoServiceImpl) {
//        this.videoService = videoService;
//        this.videoServiceImpl = videoServiceImpl;
//    }
//
//    @GetMapping("/studios")
//    public String goStudio(Model model) {
//        List<Video> videos = videoServiceImpl.getAllVideos();
//        model.addAttribute("videos", videos);
//        return "studio";
//    }
//
//    @PostMapping
//    public ResponseEntity<?> createVideo(@RequestParam("title") String title,
//                                         @RequestParam("description") String description,
//                                         @RequestParam("image") MultipartFile imageFile,
//                                         @RequestParam("video") MultipartFile videoFile,
//                                         @RequestParam("tags") List<String> tags) {
//        try {
//            String imageUrl = videoService.uploadImage(imageFile);
//            String videoUrl = videoService.uploadVideo(videoFile);
//            Video newVideo = new Video();
//            newVideo.setTitle(title);
//            newVideo.setDescription(description);
//            newVideo.setImageUrl(imageUrl);
//            newVideo.setVid1eoUrl(videoUrl);
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create video.");
//        }
//    }
//}
//
