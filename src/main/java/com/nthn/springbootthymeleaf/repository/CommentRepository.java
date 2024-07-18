package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Comment;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>,
		JpaSpecificationExecutor<Comment> {
	
	@Query("select c from Comment c where c.account.username = ?1")
	List<Comment> findByAccountUsername(
			@NotEmpty(message = "NotEmpty.account.userName") String account_username);
	
	@Query("select c from Comment c where c.news.id = ?1 and c.account.username = ?2")
	List<Comment> findByNewsIdAndAccountUsername(Integer news_id,
			@NotEmpty(message = "NotEmpty.account.userName") String account_username);
}