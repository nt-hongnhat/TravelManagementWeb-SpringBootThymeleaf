package com.nthn.springbootthymeleaf.controller.user;

import com.nthn.springbootthymeleaf.pojo.Account;
import com.nthn.springbootthymeleaf.pojo.Comment;
import com.nthn.springbootthymeleaf.pojo.Feedback;
import com.nthn.springbootthymeleaf.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("/api/")
public class APICommentController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TourService tourService;
    @Autowired
    private NewsService newsService;

    @PostMapping(value = "/news/comments", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, String> params) {
        try {
            String description = params.get("content");
            int accountId = parseInt(params.get("accountId"));
            int newsId = parseInt(params.get("newsId"));

            Comment comment = new Comment();
            comment.setContent(description);
            Account account = accountService.getAccount(accountId);
            comment.setAccount(account);
            comment.setCreatedDate(LocalDateTime.now());
            comment.setNews(newsService.getById(newsId));
            comment.setId(commentService.addComment(comment));


            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception exception) {
            System.err.println("ERROR!" + exception.getMessage());
            exception.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    @DeleteMapping("/comments/{id}")
//    public ResponseEntity<APIResponse> deleteComment() {
//        return new ResponseEntity<Comment>(deleteComment, HttpStatus.CREATED);
//    }
}
