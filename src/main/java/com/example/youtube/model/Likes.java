package com.example.youtube.model;


import com.example.youtube.enums.LikeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private LikeStatus likeStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "video_id")
    private Video video;

    public Likes(LikeStatus likeStatus, User user, Video video) {
        this.likeStatus = likeStatus;
        this.user = user;
        this.video = video;
    }
}
