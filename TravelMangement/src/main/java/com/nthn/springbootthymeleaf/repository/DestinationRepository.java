package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DestinationRepository extends JpaRepository<Destination, Integer>, JpaSpecificationExecutor<Destination> {

}