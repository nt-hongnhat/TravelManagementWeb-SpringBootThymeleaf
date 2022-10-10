package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Comment;
import com.nthn.springbootthymeleaf.repository.CommentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // CREATE
    public Integer save(Comment comment) {
        comment = commentRepository.save(comment);
        return comment.getId();
    }


    // READ
    public Comment read(Integer id) {
        return requireOne(id);
    }

    // UPDATE
    public void update(Integer id, Comment comment) {
        Comment original = requireOne(id);
        BeanUtils.copyProperties(comment, original);
        commentRepository.save(original);
    }

    // DELETE
    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    private Comment requireOne(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public CommentDTO getById(Integer id) {
//        Comment original = requireOne(id);
//        return toDTO(original);
//    }
//
//    public Page<CommentDTO> query(CommentQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private CommentDTO toDTO(Comment original) {
//        CommentDTO bean = new CommentDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
