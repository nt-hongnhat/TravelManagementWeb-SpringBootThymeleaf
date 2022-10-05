package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.CommentDTO;
import com.nthn.springbootthymeleaf.VO.CommentQueryVO;
import com.nthn.springbootthymeleaf.VO.CommentUpdateVO;
import com.nthn.springbootthymeleaf.VO.CommentVO;
import com.nthn.springbootthymeleaf.model.Comment;
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

    public Integer save(CommentVO vO) {
        Comment bean = new Comment();
        BeanUtils.copyProperties(vO, bean);
        bean = commentRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        commentRepository.deleteById(id);
    }

    public void update(Integer id, CommentUpdateVO vO) {
        Comment bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        commentRepository.save(bean);
    }

    public CommentDTO getById(Integer id) {
        Comment original = requireOne(id);
        return toDTO(original);
    }

    public Page<CommentDTO> query(CommentQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private CommentDTO toDTO(Comment original) {
        CommentDTO bean = new CommentDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Comment requireOne(Integer id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
