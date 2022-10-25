package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Booking;
import com.nthn.springbootthymeleaf.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    @Query("select count(c) from Customer c where c.nationality = ?1")
    long countAllByNationality(String nationality);

    @Query("select count(c) from Customer c inner join Booking b where c.id=b.customer.id ")
    long countAllByBookingsIn();

}