package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "departure_date")
public class DepartureDate implements Serializable {
    @Serial
    private static final long serialVersionUID = 8889954176852758534L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "tour_id", nullable = false)
    @ToString.Exclude
    private Tour tour;
    
    @NotNull
    @Column(name = "date", nullable = false)
    private LocalDate date;
    
    @Transient
    private String valueDate;
    
    @Column(name = "available_slot")
    private Integer availableSlot;
    
    @Transient
    private Integer orderSlot = 0;
    
    @OneToMany(mappedBy = "departureDate")
    @ToString.Exclude
    private List<Booking> bookings = new ArrayList<>();
    
    public DepartureDate(@org.jetbrains.annotations.NotNull String valueDate, @org.jetbrains.annotations.NotNull Tour tour) {
        this.valueDate = valueDate;
        if (!valueDate.isEmpty()) this.date = LocalDate.parse(valueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.availableSlot = tour.getMaxSlot();
        this.tour = tour;
    }
    
    public int getOrderSlot() {
        if (!bookings.isEmpty()) {
            AtomicInteger sum = new AtomicInteger();
            bookings.forEach(booking -> {
                sum.addAndGet(booking.getQuantity());
            });
            this.orderSlot = sum.get();
        }
        return this.orderSlot;
    }
    
    
}