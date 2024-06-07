package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Group;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Integer>, JpaSpecificationExecutor<Group> {
    Group findByName(String name);
    Group findById(int id);
    @NotNull
    List<Group> findAll();
    List<Group> findByNameContaining(String name);
}