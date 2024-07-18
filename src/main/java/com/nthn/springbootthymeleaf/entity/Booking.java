package com.nthn.springbootthymeleaf.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "booking")
public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate = LocalDateTime.now();
    
    @Column(name = "total")
    private BigDecimal total;
    
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    
    @Column(name = "quantity_ticket")
    private Integer quantity;
    
    @Column(name = "note")
    private String note;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_date_id")
    private DepartureDate departureDate;
    
    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetails = new ArrayList<>();
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id")
    private Payment payment;
    
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
    
    @Override
    public String toString() {
        return "Booking{" + "quantity=" + quantity + ", total=" + total + ", bookingDate=" + bookingDate + ", id=" + id + ", departureDate=" + departureDate + '}';
    }
}
