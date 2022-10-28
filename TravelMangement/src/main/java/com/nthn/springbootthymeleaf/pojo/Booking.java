package com.nthn.springbootthymeleaf.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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


    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "booking_id", insertable = false, updatable = false)
    @ToString.Exclude
    private Set<BookingDetail> bookingDetails = new LinkedHashSet<>();

    @ManyToMany
    @JoinTable(name = "booking_detail",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_ticket_id"))
    @ToString.Exclude
    private Set<TourTicket> tourTickets = new LinkedHashSet<>();


    @Transient
    private int count;
    @Transient
    private int numberAdult = 1;
    @Transient
    private int numberChildren = 0;
    @Transient
    private int numberYoungChildren = 0;
    @Transient
    private int numberInfants = 0;


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
