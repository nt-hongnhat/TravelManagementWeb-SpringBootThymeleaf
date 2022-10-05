package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.DTO.FeedbackDTO;
import com.nthn.springbootthymeleaf.VO.FeedbackQueryVO;
import com.nthn.springbootthymeleaf.VO.FeedbackUpdateVO;
import com.nthn.springbootthymeleaf.VO.FeedbackVO;
import com.nthn.springbootthymeleaf.model.Feedback;
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

    public Integer save(FeedbackVO vO) {
        Feedback bean = new Feedback();
        BeanUtils.copyProperties(vO, bean);
        bean = feedbackRepository.save(bean);
        return bean.getId();
    }

    public void delete(Integer id) {
        feedbackRepository.deleteById(id);
    }

    public void update(Integer id, FeedbackUpdateVO vO) {
        Feedback bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        feedbackRepository.save(bean);
    }

    public FeedbackDTO getById(Integer id) {
        Feedback original = requireOne(id);
        return toDTO(original);
    }

    public Page<FeedbackDTO> query(FeedbackQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private FeedbackDTO toDTO(Feedback original) {
        FeedbackDTO bean = new FeedbackDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Feedback requireOne(Integer id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
