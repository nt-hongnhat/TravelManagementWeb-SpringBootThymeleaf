package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface TransportRepository extends JpaRepository<Transport, Integer>, JpaSpecificationExecutor<Transport> {

}