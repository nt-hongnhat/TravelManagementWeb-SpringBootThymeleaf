package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tour_schedule")
public class TourSchedule implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    @ToString.Exclude
    public Tour tour;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "ordinal_date", nullable = false)
    private Integer ordinalDate;
    
    @Column(name = "itinerary", nullable = false)
    private String itinerary;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    public TourSchedule(int ordinalDate, String itinerary, String description, Tour tour) {
        this.ordinalDate = ordinalDate;
        this.itinerary = itinerary;
        this.description = description;
        this.tour = tour;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourSchedule that = (TourSchedule) o;
        return id != null && Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
