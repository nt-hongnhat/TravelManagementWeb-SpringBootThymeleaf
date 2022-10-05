package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.TourPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository

public interface TourPriceRepository extends JpaRepository<TourPrice, Integer>, JpaSpecificationExecutor<TourPrice> {

}