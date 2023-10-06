package com.example.youtube.Service.comment;

import com.example.youtube.model.Comment;
import com.example.youtube.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public void addComment (Comment comment){
        commentRepository.save(comment);
    }
    public Comment findById(int id){
        return commentRepository.findById(id);
    }
    public List<Comment> getReply(int id){
        return commentRepository.findByComment_Id(id);
    }
}
