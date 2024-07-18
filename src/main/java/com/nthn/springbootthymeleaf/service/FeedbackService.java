package com.nthn.springbootthymeleaf.service;

import com.nthn.springbootthymeleaf.entity.Feedback;

import java.util.List;
import java.util.Map;

public interface FeedbackService {
    Integer save(Feedback feedback);

    Feedback add(int tourId, int accountId, String description, double rating);

    void delete(Integer id);

    void update(Integer id, Feedback feedback);

    Feedback getById(Integer id);

    List<Feedback> getFeedbacks(double rating);

    List<Feedback> getFeedbacks(Map<String, String> params);


}
