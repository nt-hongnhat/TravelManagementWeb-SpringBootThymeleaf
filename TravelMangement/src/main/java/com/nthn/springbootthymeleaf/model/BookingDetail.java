package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "booking_detail")
public class BookingDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;


    @Column(name = "booking_id", nullable = false)
    private Integer bookingId;

    @Column(name = "surcharge_id")
    private Integer surchargeId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 1;

    @Column(name = "discount", nullable = false)
    private Double discount = 0.0D;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BookingDetail that = (BookingDetail) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    private Surcharge surcharge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "surcharge_id")
    public Surcharge getSurcharge() {
        return surcharge;
    }

    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id")
    public Booking getBooking() {
        return booking;
    }
}
