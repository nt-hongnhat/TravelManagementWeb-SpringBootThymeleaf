package com.nthn.springbootthymeleaf.pojo;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "surcharge")
public class Surcharge implements Serializable {

    @Id
    @Column(name = "tour_ticket_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private BigDecimal surcharge;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Surcharge surcharge = (Surcharge) o;
        return id != null && Objects.equals(id, surcharge.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
