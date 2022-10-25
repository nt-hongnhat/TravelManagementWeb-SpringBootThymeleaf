package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.pojo.Feedback;

import java.util.List;
import java.util.Map;

public interface FeedbackService {
    Integer save(Feedback feedback);

    void delete(Integer id);

    void update(Integer id, Feedback feedback);

    Feedback getById(Integer id);

    List<Feedback> getFeedbacks(double rating);

    List<Feedback> getFeedbacks(Map<String, String> params);


}
