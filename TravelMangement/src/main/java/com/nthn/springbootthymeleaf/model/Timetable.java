package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "timetable")
public class Timetable implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Destination destination;

    private Tour tour;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tour_id", nullable = false)
    private Integer tourId;

    @Column(name = "destination_id", nullable = false)
    private Integer destinationId;

    @Column(name = "ordinal_date", nullable = false)
    private Integer ordinalDate;

    @Column(name = "timer", nullable = false)
    private LocalTime timer;

    @Column(name = "activities", nullable = false)
    private String activities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    public Destination getDestination() {
        return destination;
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
        Timetable timetable = (Timetable) o;
        return id != null && Objects.equals(id, timetable.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
