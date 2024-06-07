package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @Column(name = "id", length = -1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_date_id")
    private DepartureDate departureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "quantity", length = -1)
    private Integer quantity;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "payment")
    private Boolean payment;

    @Column(name = "booking_date")
    private LocalDateTime bookingDate = LocalDateTime.now();


}
