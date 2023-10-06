package com.example.youtube.controller.api;
import com.example.youtube.Service.AuthService;
import com.example.youtube.Service.comment.CommentService;
import com.example.youtube.Service.video.VideoService;
import com.example.youtube.model.Comment;
import com.example.youtube.model.User;
import com.example.youtube.model.Video;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentResController {
    private final CommentService commentService;
    private final AuthService authService;
    private final VideoService videoService;

    public CommentResController(CommentService commentService, AuthService authService, VideoService videoService) {
        this.commentService = commentService;
        this.authService = authService;
        this.videoService = videoService;
    }

    @PostMapping
    public void addComment(@RequestBody Comment comment) {
        if( comment.getTitle()!= ""){
            User user=  authService.findByName(authService.getCurrentUser());
            if(user!= null){
                comment.setUser(user);
                commentService.addComment(comment);
            }
        }
    }
    @GetMapping("getComment")
    public ResponseEntity<?> showComment (@RequestParam int id) {
        Video video = videoService.findById(id);
        List<Comment> comments = video.getComments();
        Collections.reverse(comments);
        return ResponseEntity.ok(comments);
    }
    @PostMapping("addReply")
    public void addReply( @RequestBody Comment comment ,@RequestParam int commentId ){
      Comment c=  commentService.findById(commentId);
        User user=  authService.findByName(authService.getCurrentUser());
      comment.setComment(c);
      comment.setUser(user);
        if(user!= null){
            comment.setComment(c);
            comment.setUser(user);
            commentService.addComment(comment);
        }
    }
    @GetMapping("getReply")
    public ResponseEntity<?> getReply(@RequestParam int commentId){
        List<Comment> comments =commentService.getReply(commentId);
        return ResponseEntity.ok(comments);
    }

}

