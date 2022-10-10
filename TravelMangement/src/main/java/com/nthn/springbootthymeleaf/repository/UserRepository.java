package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query("select u from User u where u.username like concat('%', ?1, '%')")
    List<User> findByUsernameContaining(String username);

    @Query("select u from User u where u.email like concat('%', ?1, '%')")
    List<User> findByEmailContaining(String email);

    @Query("select (count(u) > 0) from User u where u.username = ?1")
    boolean existsByUsername(String username);
}