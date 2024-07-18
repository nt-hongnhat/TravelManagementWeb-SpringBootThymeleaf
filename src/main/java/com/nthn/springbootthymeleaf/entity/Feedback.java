package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "description")
    private String description;

    @Column(name = "rating", nullable = false)
    private Double rating;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Feedback feedback = (Feedback) o;
        return id != null && Objects.equals(id, feedback.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
