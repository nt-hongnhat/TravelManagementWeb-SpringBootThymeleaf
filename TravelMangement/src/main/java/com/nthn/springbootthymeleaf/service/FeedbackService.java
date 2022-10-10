package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Feedback;
import com.nthn.springbootthymeleaf.repository.FeedbackRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Integer save(Feedback feedback) {
        Feedback bean = new Feedback();
        BeanUtils.copyProperties(feedback, bean);
        bean = feedbackRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        feedbackRepository.deleteById(id);
    }

    public void update(Integer id, Feedback feedback) {
        Feedback bean = requireOne(id);
        BeanUtils.copyProperties(feedback, bean);
        feedbackRepository.save(bean);
    }

    public Feedback getById(Integer id) {
        return (requireOne(id));
    }

    private Feedback requireOne(Integer id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

//    public Page<FeedbackDTO> query(FeedbackQueryVO vO) {
//        throw new UnsupportedOperationException();
//    }
//
//    private FeedbackDTO toDTO(Feedback original) {
//        FeedbackDTO bean = new FeedbackDTO();
//        BeanUtils.copyProperties(original, bean);
//        return bean;
//    }
}
