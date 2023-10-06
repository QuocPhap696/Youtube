package com.example.youtube.Service.like;

import com.example.youtube.enums.LikeStatus;
import com.example.youtube.model.Likes;
import com.example.youtube.model.User;
import com.example.youtube.model.Video;
import com.example.youtube.repository.LikesRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikesRepository likesRepository;

    public LikeService(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }
    public void create(Likes likes){
        likesRepository.save(likes);
    }
    public Likes findByUserAndVideo(User user,Video video){
        Likes likes=likesRepository.findLikesByUserIdAndVideoId(user.getId(),video.getId());
        return likes;
    }
    public void deleteLike(Likes likes){
        likesRepository.delete(likes);
    }
    public int countLike(Video video){
        return likesRepository.countLikesByVideoIdAndLikeStatus(video.getId());
    }
    public int countDisLike(Video video){
        return likesRepository.countDisLikesByVideoIdAndLikeStatus(video.getId());
    }
}
