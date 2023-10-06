package com.example.youtube.repository;

import com.example.youtube.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findById(int id);
    List<Comment> findByComment_Id(int comment_id);
}
