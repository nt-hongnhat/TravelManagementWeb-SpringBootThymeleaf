package com.nthn.springbootthymeleaf.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Accessors(chain = true)
@Table(name = "booking_detail")
public class BookingDetail implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


//    @Column(name = "booking_id", nullable = false)
//    private Integer bookingId;
//
//    @Column(name = "tour_ticket_id", nullable = false)
//    private Integer tourTicketId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Transient
    private BigDecimal total;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tour_ticket_id", nullable = false)
    private TourTicket tourTicket;


}
