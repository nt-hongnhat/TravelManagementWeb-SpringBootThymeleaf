package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface DestinationRepository extends JpaRepository<Destination, Integer>, JpaSpecificationExecutor<Destination> {

}