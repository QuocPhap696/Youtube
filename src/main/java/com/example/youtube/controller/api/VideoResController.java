package com.example.youtube.controller.api;
import com.example.youtube.Service.AuthService;
import com.example.youtube.Service.tagDetail.request.TagDetailSaveRequest;
import com.example.youtube.Service.video.VideoService;
import com.example.youtube.Service.video.request.VideoSaveRequest;
import com.example.youtube.enums.TypeVideo;
import com.example.youtube.model.Tag;
import com.example.youtube.model.TagDetail;
import com.example.youtube.model.User;
import com.example.youtube.model.Video;
import com.example.youtube.repository.TagRepository;
import com.example.youtube.repository.UserRepository;
import com.example.youtube.repository.VideoRepository;
import com.example.youtube.Service.TagDetailService;
import com.example.youtube.Service.TagService;
import com.example.youtube.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/videos")
public class VideoResController {
    private final TagRepository tagRepository;
    private VideoService videoService;
    private TagDetailService tagDetailService;
    private TagService tagService;
    private UserRepository userRepository;
    private final VideoRepository videoRepository;
    public final AuthService authService;
    @Autowired
    private ResourceLoader resourceLoader;

    public VideoResController(TagRepository tagRepository, VideoService videoService, TagDetailService tagDetailService, TagService tagService, UserRepository userRepository, VideoRepository videoRepository, AuthService authService) {
        this.tagRepository = tagRepository;
        this.videoService = videoService;
        this.tagDetailService = tagDetailService;
        this.tagService = tagService;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.authService = authService;
    }


    @GetMapping
    public List<Video> showSearch() {
        List<Video> videos = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        User user = authService.findByName(authService.getCurrentUser());
        if(user !=null){
            List<Object[]> a = videoRepository.findTopTwoTagsByUserId(user.getId());
            for (var tag : a) {
                List<Integer> b = videoRepository.findVideoIdsByTagTitle((String) tag[0]);
                arr.addAll(b);
            }
            if(arr.size()>0) {
                HashSet<Integer> set = new HashSet<>(arr);
                List<Integer> listWithoutDuplicates = new ArrayList<>(set);
                for (var item : listWithoutDuplicates) {
                    videos.add(videoRepository.findById(item.intValue()));
                }
                Collections.shuffle(videos);
                return videos;

            }else {

                    List<Video> videoList=videoRepository.findAll();
                    Collections.shuffle(videoList);
                    return videoList ;
                }
            }


        List<Video> videoList=videoRepository.findAll();
        Collections.shuffle(videoList);
        return videoList ;

    }

    @GetMapping("/createVideo")
    @ResponseBody
    public ResponseEntity<List<Tag>> showCreate() {
        List<Tag> tags = tagService.findAll();
        return ResponseEntity.ok(tags);
    }

        @PostMapping("/createVideo")
        @ResponseBody
        public ResponseEntity<?> createVideo( @ModelAttribute com.example.youtube.Service.video.request.VideoSaveRequest videoSaveRequest) {

            User userLog = authService.findByName(authService.getCurrentUser());

            if (userLog == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
            }

            String title = videoSaveRequest.getTitle();
            String description = videoSaveRequest.getDescription();
            MultipartFile imgFile = videoSaveRequest.getImg();
            MultipartFile videoFile = videoSaveRequest.getVideo();
            List<TagDetailSaveRequest> tagDetails = videoSaveRequest.getTagDetails();

            if ( imgFile.isEmpty()) {
                return ResponseEntity.badRequest().body("Video and image files are required.");
            }

            if (title.isEmpty() || tagDetails.isEmpty()) {
                return ResponseEntity.badRequest().body("Title and tags are required.");
            }

            try {
                Resource resource = resourceLoader.getResource("classpath:/src/main/resources/static/assets/video/");
                String templatePath = resource.getFile().getAbsolutePath();
                Path p1 = Paths.get(templatePath + File.separator + videoFile.getOriginalFilename());
                InputStream inputStream1 = videoFile.getInputStream();
                Files.copy(inputStream1, p1, StandardCopyOption.REPLACE_EXISTING);

                Resource resource2 = resourceLoader.getResource("classpath:/src/main/resources/static/assets/images/video_images");
                String templatePath2 = resource2.getFile().getAbsolutePath();
                Path p2 = Paths.get(templatePath2 + File.separator + imgFile.getOriginalFilename());
                InputStream inputStream2 = imgFile.getInputStream();
                Files.copy(inputStream2, p2, StandardCopyOption.REPLACE_EXISTING);

                Video video = new Video();
                video.setTitle(title);
                video.setDescription(description);
                video.setVideo(p1.toString().split("assets")[1]);
                video.setImg(p2.toString().split("assets")[1]);
                video.setUser(userLog);
                video.setDateSubmit(LocalDateTime.now());

                List<TagDetail> tags = new ArrayList<>();
                for (TagDetailSaveRequest tagReq : tagDetails) {
                    TagDetail tagDetail = new TagDetail();
                    Tag tag = tagService.findById(Integer.parseInt(tagReq.getTag()));
                    tagDetail.setTag(tag);
                    tagDetail.setVideo(video);
                    tags.add(tagDetail);
                }
                video.setTagDetails(tags);

                videoService.create(video);

                return ResponseEntity.ok("Video uploaded successfully.");
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while uploading the video.");
            }
    }


    @GetMapping("/edit")
    public ResponseEntity<?> showEdit(@RequestParam int id) {
        Video video = videoService.findById(id);
        List<Tag> tagList = tagService.findAll();
        Map<Object, String> map = new HashMap<>();
        map.put(video, "video");
        map.put(tagList, "tag");
        return ResponseEntity.ok(map);
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Video video) {
        videoService.update(video);
        return ResponseEntity.ok("edit");
    }

    @GetMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam int id) {
        videoService.delete(id);
        return ResponseEntity.ok("delete");
    }

    @GetMapping("/findtitle")
    public ResponseEntity<?> findByTitleOrUsername(@RequestParam String search) {
        List<Video> videoList = videoService.findByTitleContainingOrUsername(search, search);
        return ResponseEntity.ok(videoList);
    }

    @GetMapping("/tag/{id}")
    public ResponseEntity<?> showTags(@PathVariable int id){
//        Tag tag = tagService.findById(id);
        List<Video> videos = videoService.findVideosByTagId(id);

        return ResponseEntity.ok(videos);
    }

    @GetMapping("/tags")
    public ResponseEntity<?> getTags() {
        List<Tag> tagList = tagService.findAll();
        return ResponseEntity.ok(tagList);
    }
    @GetMapping("getVideoLiked")
    public ResponseEntity<?> getVideoLiked() {
        User user = authService.findByName(authService.getCurrentUser());
        if (user != null) {
            List<Video> videos = videoService.findVideoUserLiked(user.getId());
            return ResponseEntity.ok(videos);
        }
        return null;
    }

    @GetMapping("getVideoOfUserLog")
    public ResponseEntity<?> getVideoOfUserLog(){
        User user = authService.findByName(authService.getCurrentUser());
        if (user != null) {

            return ResponseEntity.ok(videoService.findVideosByUserId(user.getId()));
        }
        return null;

    }

}
