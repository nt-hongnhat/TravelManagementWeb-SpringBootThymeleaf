package com.nthn.springbootthymeleaf.entity;

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
@Table(name = "work_for")
public class WorkFor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Agency agency;

    private Employee employee;

    @Column(name = "hire_date", nullable = false)
    private LocalDateTime hireDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "agency_id", nullable = false)
    public Agency getAgency() {
        return agency;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WorkFor workFor = (WorkFor) o;
        return id != null && Objects.equals(id, workFor.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
