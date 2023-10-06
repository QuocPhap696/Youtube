
package com.example.youtube.Service.video;

import com.example.youtube.Service.video.request.VideoSaveRequuest;
import com.example.youtube.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideosService {



        List<Video> getAllVideos();

        Video createVideo(VideoSaveRequuest videoSaveRequuest, MultipartFile imageFile, MultipartFile videoFile) throws IOException;


}


