package com.example.youtube.Service.video.respone;

import com.example.youtube.enums.TypeVideo;
import com.example.youtube.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoListRespone {
    private String title;
    private TypeVideo typeVideo;
    private LocalDate dateSubmit;
    private String video;
    private String img;
    private User user;
}
