package com.example.youtube.repository;
import com.example.youtube.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video,Integer> {



    Page<Video> findByTitleContainingOrUser_Username(String title, String user_username, Pageable pageable);
    @Query("SELECT v.id FROM Video v " +
            "LEFT JOIN TagDetail td ON v.id = td.video.id " +
            "LEFT JOIN Tag t ON td.tag.id = t.id " +
            "WHERE t.title LIKE %:keyword1%  " +
            "GROUP BY v.id")
    List<Integer> findVideoIdsByTagTitle( String keyword1);

    @Query("SELECT v FROM Video v " +
            "LEFT JOIN TagDetail td ON v.id = td.video.id " +
            "LEFT JOIN Tag t ON td.tag.id = t.id " +
            "WHERE t.id = :id ")

    List<Video> findVideosByTagId(Integer id);

    @Query(value = "SELECT v FROM Video v " +
            "JOIN User u ON v.user.id = u.id " +
            "WHERE v.title LIKE %:title% OR u.username LIKE %:username%")

    List<Video> findByTitleContainingOrUser_Username(String title, String username);
    @Query("SELECT t.title, COUNT(*) FROM User u " +
            "inner JOIN View v ON u.id = v.user.id " +
            "inner JOIN Video vi ON v.video.id = vi.id " +
            "inner JOIN TagDetail td ON vi.id = td.video.id " +
            "inner JOIN Tag t ON td.tag.id = t.id " +
            "WHERE u.id = :userId " +
            "GROUP BY t.title " +
            "ORDER BY COUNT(*) DESC "
         )
    List<Object[]> findTopTwoTagsByUserId(int userId);
    Video findById(int id);
    @Query("SELECT v FROM Likes l JOIN Video v ON l.video.id = v.id WHERE l.likeStatus = 'LIKE' AND l.user.id = :userId")
    List<Video> findLikedVideosByUserId( int userId);
    @Query("SELECT v FROM Video v WHERE v.user.id = :userId")
    List<Video> findVideosByUserId(int userId);
    @Query("SELECT v FROM Video v WHERE v.dateSubmit >= :startDate ORDER BY v.viewCount DESC")
    List<Video> findTopTrendingVideosAfterDate(LocalDateTime startDate);
}

