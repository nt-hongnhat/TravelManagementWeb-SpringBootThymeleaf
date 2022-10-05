package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Tour tour;

    private Customer customer;

    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "customer_id")
    private Integer customerId;

    private Employee employee;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate = LocalDateTime.now();

    @Column(name = "total", nullable = false)
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    public Employee getEmployee() {
        return employee;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    public Tour getTour() {
        return tour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Booking booking = (Booking) o;
        return id != null && Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
