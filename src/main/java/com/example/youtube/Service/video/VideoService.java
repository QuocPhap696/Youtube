package com.example.youtube.Service.video;

import com.example.youtube.model.User;
import com.example.youtube.model.Video;
import com.example.youtube.model.View;
import com.example.youtube.repository.UserRepository;
import com.example.youtube.repository.VideoRepository;
import com.example.youtube.repository.ViewRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
public class VideoService {
    private static final String IMAGE_UPLOAD_PATH = "/assets/images/video_images";
    private static final String VIDEO_UPLOAD_PATH = "/assets/video";
    private final VideoRepository videoRepository;
    private final ViewRepository viewRepository;
    private final UserRepository userRepository;

    public VideoService(VideoRepository videoRepository, ViewRepository viewRepository, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.viewRepository = viewRepository;
        this.userRepository = userRepository;
    }

    public List<Video> findAll(){
        return videoRepository.findAll();
    }
    public void createVideo(Video video){
        videoRepository.save(video);
    }

    public Video create(Video video){

        return videoRepository.save(video);
    }

    public void update(Video video){
        videoRepository.save(video);
    }

    public Video findById(int id){
        return videoRepository.findById(id);
    }

    public void delete(int id){
        videoRepository.deleteById(id);
    }
    public List<Video> findByTitleContainingOrUsername(String title,String user_username){

        return videoRepository.findByTitleContainingOrUser_Username(title, user_username);
    }

    public List<Video> findVideosByTagId(int id) {
        return videoRepository.findVideosByTagId(id);
    }

    public String uploadImage(MultipartFile imageFile) {
        try {
            String filename = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
            Files.copy(imageFile.getInputStream(), Paths.get(IMAGE_UPLOAD_PATH, filename),
                    StandardCopyOption.REPLACE_EXISTING);

            return "/images/" + filename; // Example: "/images/unique_filename.jpg"
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload image.");
        }
    }
    public String uploadVideo(MultipartFile videoFile) {
        try {
            String filename = UUID.randomUUID().toString() + "_" + videoFile.getOriginalFilename();
            Files.copy(videoFile.getInputStream(), Paths.get(VIDEO_UPLOAD_PATH, filename),
                    StandardCopyOption.REPLACE_EXISTING);
            return "/videos/" + filename;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to upload video.");
        }
    }
    public Video getSingleUserView(int videoId, String username){
        Video video = videoRepository.findById(videoId);
        User user = userRepository.findByUsername(username);
        View view = new View();
        view.setUser(user);
        view.setVideo(video);
        view.setViewdate(LocalDate.now());
        viewRepository.save(view);
        video.setViewCount(video.getViewCount()+1);
        videoRepository.save(video);
        return video;
    }
    public List<Video> getTopTrendingVideosLast30Days() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
        return videoRepository.findTopTrendingVideosAfterDate(thirtyDaysAgo);
    }

    public List<Video> findVideoUserLiked(int id){
        return videoRepository.findLikedVideosByUserId(id);
    }
    public List<Video> findVideosByUserId(int id){
        return videoRepository.findVideosByUserId(id);
    }

}
