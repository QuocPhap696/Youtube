
package com.example.youtube.Service.video;

import com.example.youtube.Service.video.VideosService;
import com.example.youtube.Service.video.request.VideoSaveRequuest;
import com.example.youtube.model.Tag;
import com.example.youtube.model.TagDetail;
import com.example.youtube.model.Video;
import com.example.youtube.repository.TagDetailRepository;
import com.example.youtube.repository.TagRepository;
import com.example.youtube.repository.VideoRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

    @Service
    public class VideoServiceImpl implements VideosService {
        private final VideoRepository videoRepository;
        private final TagRepository tagRepository;
        private final TagDetailRepository tagDetailRepository;

        @Value("${upload.path}")
        private String uploadPath;

        public VideoServiceImpl(VideoRepository videoRepository, TagRepository tagRepository, TagDetailRepository tagDetailRepository) {
            this.videoRepository = videoRepository;
            this.tagRepository = tagRepository;
            this.tagDetailRepository = tagDetailRepository;
        }

        @Override
        public List<Video> getAllVideos() {
            return videoRepository.findAll();
        }

        @Override
        @Transactional
        public Video createVideo(VideoSaveRequuest videoSaveRequuest, MultipartFile imageFile, MultipartFile videoFile) throws IOException, IOException {
            Video newVideo = new Video();
            newVideo.setTitle(videoSaveRequuest.getTitle());
            newVideo.setDescription(videoSaveRequuest.getDescription());

            String imageExtension = imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf(".") + 1);
            String imageFileName = System.currentTimeMillis() + "." + imageExtension;
            FileUtils.writeByteArrayToFile(new File(uploadPath, imageFileName), imageFile.getBytes());
            newVideo.setImg(imageFileName);

            String videoExtension = videoFile.getOriginalFilename().substring(videoFile.getOriginalFilename().lastIndexOf(".") + 1);
            String videoFileName = System.currentTimeMillis() + "." + videoExtension;
            FileUtils.writeByteArrayToFile(new File(uploadPath, videoFileName), videoFile.getBytes());
            newVideo.setVideo(videoFileName);
            newVideo = videoRepository.save(newVideo);

            for (Integer tagId : videoSaveRequuest.getTagIds()) {
                Optional<Tag> tagOptional = tagRepository.findById(tagId);
                Video finalNewVideo = newVideo;
                tagOptional.ifPresent(tag -> {

                    TagDetail tagDetail = new TagDetail();
                    tagDetail.setTag(tag);
                    tagDetail.setVideo(finalNewVideo);
                    tagDetailRepository.save(tagDetail);
                });
            }

            return newVideo;
        }
    }
