
package com.example.youtube.repository;

import com.example.youtube.model.Likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes,Integer> {
    @Query("SELECT l FROM Likes l WHERE l.user.id = :userId AND l.video.id = :videoId")
    Likes findLikesByUserIdAndVideoId( int userId,  int videoId);
    @Query("SELECT COUNT(l) FROM Likes l WHERE l.video.id = :videoId AND l.likeStatus = 'LIKE'")
    int countLikesByVideoIdAndLikeStatus( int videoId);
    @Query("SELECT COUNT(l) FROM Likes l WHERE l.video.id = :videoId AND l.likeStatus = 'DISLIKE'")
    int countDisLikesByVideoIdAndLikeStatus( int videoId);
}
