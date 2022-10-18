package com.nthn.springbootthymeleaf.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tour_info")
public class Tour implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "itinerary", nullable = false)
    private String itinerary;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "max_slot", nullable = false)
    private Integer maxSlot;

    @Column(name = "transfer", nullable = false)
    private String transfer;

    @Column(name = "departure_place", nullable = false)
    private String departurePlace;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_group_id", nullable = false)
    private TourGroup tourGroup;


    @OneToMany(mappedBy = "tour", orphanRemoval = true)
    private Set<TourSchedule> tourSchedules = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tour tour = (Tour) o;
        return id != null && Objects.equals(id, tour.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
