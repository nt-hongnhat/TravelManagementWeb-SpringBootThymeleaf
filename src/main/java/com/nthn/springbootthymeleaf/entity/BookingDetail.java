package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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
    
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    
    @Transient
    private BigDecimal total;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "booking_id", nullable = false)
    @ToString.Exclude
    private Booking booking;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "tour_ticket_id", nullable = false)
    @ToString.Exclude
    private TourTicket tourTicket;
    
    public BookingDetail(Booking booking, @NotNull TourTicket tourTicket) {
        this.booking = booking;
        this.tourTicket = tourTicket;
        this.quantity = 0;
        this.unitPrice = tourTicket.getUnitPrice();
    }
    
    public BigDecimal getTotal() {
        if (quantity != null) {
            total = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
        return total;
    }
    
    @Override
    public String toString() {
        return "BookingDetail{" + "unitPrice=" + unitPrice + ", tourTicket=" + tourTicket + ", total=" + total + ", quantity=" + quantity + ", id=" + id + '}';
    }
}
