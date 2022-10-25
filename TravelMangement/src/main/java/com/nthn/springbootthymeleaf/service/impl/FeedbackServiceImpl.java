package com.nthn.springbootthymeleaf.service.impl;

import com.nthn.springbootthymeleaf.pojo.Feedback;
import com.nthn.springbootthymeleaf.repository.FeedbackRepository;
import com.nthn.springbootthymeleaf.repository.TourRepository;
import com.nthn.springbootthymeleaf.service.FeedbackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private TourRepository tourRepository;

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

    public List<Feedback> getFeedbacks(double rating) {
        return feedbackRepository.findByRatingGreaterThanEqual(rating);
    }

    public List<Feedback> getFeedbacks(Map<String, String> params) {
        Sort sort = Sort.by("id");
        if (params.isEmpty()) {
            return feedbackRepository.findAll(sort);
        } else {
            // Tìm kiếm theo rating
            double minRating = Double.parseDouble(params.get("minRating"));
            double maxRating = Double.parseDouble(params.get("maxRating"));

            // Tìm kiếm theo tour
            int tourId = Integer.parseInt(params.get("tourId"));
            if (tourRepository.existsById(tourId))
                feedbackRepository.findByTourId(tourId);

            // Tìm kiếm theo nội dung
            String keyword = params.get("keyword");
            if (keyword.isEmpty())
                feedbackRepository.findByDescriptionContainingIgnoreCase(keyword);
        }

        return null;
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
