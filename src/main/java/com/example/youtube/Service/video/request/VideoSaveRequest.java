package com.example.youtube.Service.video.request;

import com.example.youtube.Service.tagDetail.request.TagDetailSaveRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoSaveRequest {
    private String title;
    private String description;
    private MultipartFile img;
    private MultipartFile video;
    private List<TagDetailSaveRequest> tagDetails;

//    public VideoSaveRequuest() {
//    }
//
//    public VideoSaveRequuest(String title, String typeVideo, String video, String img) {
//        this.title = title;
//        this.typeVideo = typeVideo;
//        this.video = video;
//        this.img = img;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getTypeVideo() {
//        return typeVideo;
//    }
//
//    public void setTypeVideo(String typeVideo) {
//        this.typeVideo = typeVideo;
//    }
//
//    public String getVideo() {
//        return video;
//    }
//
//    public void setVideo(String video) {
//        this.video = video;
//    }
//
//    public String getImg() {
//        return img;
//    }
//
//    public void setImg(String img) {
//        this.img = img;
//    }
}
