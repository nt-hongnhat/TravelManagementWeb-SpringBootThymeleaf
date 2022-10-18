package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer>, JpaSpecificationExecutor<BookingDetail> {

}