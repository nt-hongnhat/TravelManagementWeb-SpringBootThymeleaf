package com.nthn.springbootthymeleaf.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@ToString
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

    @Column(name = "total", nullable = false)
    private BigDecimal total = BigDecimal.valueOf(0);

    @Column(name = "payment", nullable = false)
    private Boolean payment = Boolean.FALSE;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    private Customer customer;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    @ToString.Exclude
    private Tour tour;


    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<BookingDetail> bookingDetails = new ArrayList<BookingDetail>();


    //    @OneToMany(mappedBy = "booking", orphanRemoval = true)
//    private Set<BookingDetail> bookingDetails = new LinkedHashSet<>();
    @Transient
    private Integer count = 0;

    public int getCount() {
        bookingDetails.forEach(bookingDetail -> {
            count += bookingDetail.getQuantity();
        });
        return count;
    }

    public void calculateTotal() {
        bookingDetails.forEach(bookingDetail -> {
            total = total.add(bookingDetail.getUnitPrice().multiply(BigDecimal.valueOf(bookingDetail.getQuantity())));
            System.out.println(bookingDetail.getTourTicket().getUnitPrice() + "--------" + bookingDetail.getQuantity());
        });
    }

    @Transient
    private Integer numberAdult = 1;
    @Transient
    private Integer numberChildren = 0;
    @Transient
    private Integer numberYoungChildren = 0;
    @Transient
    private Integer numberInfants = 0;

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
