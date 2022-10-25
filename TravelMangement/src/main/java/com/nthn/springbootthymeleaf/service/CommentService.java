package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Comment;

import java.util.List;

public interface CommentService {
    Integer save(Comment comment);

    Comment read(Integer id);

    void update(Integer id, Comment comment);

    void delete(Integer id);

}
