package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "transfer")
public class Transfer implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tour_id")
    private Integer tourId;

    @Column(name = "transport_id")
    private Integer transportId;

    /**
     * Giờ khởi hành dự kiến
     */
    @Column(name = "ET_departure", nullable = false)
    private LocalDateTime etDeparture;

    /**
     * Giờ đến dự kiến
     */
    @Column(name = "ET_arrival", nullable = false)
    private LocalDateTime etArrival;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transfer transfer = (Transfer) o;
        return id != null && Objects.equals(id, transfer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
