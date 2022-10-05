package com.nthn.springbootthymeleaf.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
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

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surcharge", nullable = false)
    private Double surcharge;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    /**
     * Hạn sử dụng
     */
    @Column(name = "expiry_date", nullable = false)
    private LocalDateTime expiryDate;


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
