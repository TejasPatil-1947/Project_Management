package com.project.controller;

import com.project.Model.Comment;
import com.project.Model.User;
import com.project.request.CreateCommentRequest;
import com.project.response.MessageResponse;
import com.project.service.CommentService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception{

        User user = userService.findUserProfileByJwt(jwt);
        Comment createdContent = commentService.createComment(req.getIssueId(), user.getId(), req.getContent());

        return new ResponseEntity<>(createdContent, HttpStatus.CREATED);
    }

    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization") String jwt

    )throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());

        MessageResponse messageResponse= new MessageResponse();
        messageResponse.setMessage("Comment deleted successfully");
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentByIssueId(@PathVariable Long issueId){
        List<Comment> commentByIssueId =
                commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(commentByIssueId,HttpStatus.OK);
    }


}
