package com.nthn.springbootthymeleaf.pojo;

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

    @Column(name = "agency_id", nullable = false)
    private Integer agencyId;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "hire_date", nullable = false)
    private LocalDateTime hireDate = LocalDateTime.now();

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
