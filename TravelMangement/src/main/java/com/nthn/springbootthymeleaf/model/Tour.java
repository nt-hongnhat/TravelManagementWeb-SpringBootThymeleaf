package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tour")
public class Tour implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * Số ngày
     */
    @Column(name = "days", nullable = false)
    private Integer days;

    /**
     * Số đêm
     */
    @Column(name = "night", nullable = false)
    private Integer night;

    @Column(name = "slots", nullable = false)
    private Integer slots;

    @Column(name = "departure", nullable = false)
    private String departure;

    @Column(name = "destination", nullable = false)
    private String destination;

    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    public Category getCategory() {
        return category;
    }


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
