package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Comment;

public interface CommentService {

    int addComment(Comment comment);

    Integer save(Comment comment);

    Comment read(Integer id);

    void update(Integer id, Comment comment);

    void delete(Integer id);

}
