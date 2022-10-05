package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>, JpaSpecificationExecutor<Feedback> {

}