package com.nthn.springbootthymeleaf.model;

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
@Table(name = "customer")
public class Customer implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "identifiedcard", nullable = false)
    private String identifiedcard;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Customer customer = (Customer) o;
        return id != null && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
}
