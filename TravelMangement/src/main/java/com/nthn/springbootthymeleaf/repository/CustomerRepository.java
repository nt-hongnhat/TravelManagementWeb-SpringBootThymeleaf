package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer> {

    @Query("select count(c) from Customer c where c.nationality = ?1")
    long countAllByNationality(String nationality);

    @Query("select count(c) from Customer c inner join Booking b where c.id=b.customer.id ")
    long countAllByBookingsIn();

    @Query("select c from Customer c where c.identified = ?1")
    Customer getCustomerByIdentified(String identified);


    @Query("select c from Customer c where c.account.id = ?1")
    Customer findByAccountId(Integer id);

}