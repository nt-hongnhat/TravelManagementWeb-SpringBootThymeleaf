package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}