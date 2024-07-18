package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Feedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>,
		JpaSpecificationExecutor<Feedback> {
	
	@Query("select f from Feedback f where f.rating >= ?1")
	List<Feedback> findByRatingGreaterThanEqual(double rating);
	
	@Query("select f from Feedback f where f.tour.id = ?1")
	List<Feedback> findByTourId(Integer tourId);
	
	@Query("select f from Feedback f where f.account.id = ?1")
	List<Feedback> findByAccountId(Integer accountId);
	
	@Query("select f from Feedback f where upper(f.description) like upper(concat('%', ?1, '%'))")
	List<Feedback> findByDescriptionContainingIgnoreCase(String keyword);
	
	@Override
	long count();
}