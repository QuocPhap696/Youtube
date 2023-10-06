package com.example.youtube.Service.respone;

import com.example.youtube.enums.TypeVideo;
import com.example.youtube.model.TagDetail;
import com.example.youtube.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class VideoListResponse {
    private int id;
    private String title;

    private TypeVideo typeVideo;
    private LocalDate dateSubmit;
    private String video;
    private String img;

    private User user;

    private List<TagDetail> tagDetails;
}
