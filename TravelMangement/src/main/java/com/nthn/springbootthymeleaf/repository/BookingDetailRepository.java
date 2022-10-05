package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.model.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, String>, JpaSpecificationExecutor<BookingDetail> {

}