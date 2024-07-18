package com.nthn.springbootthymeleaf.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstname;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastname;
    
    @Size(max = 10)
    @Column(name = "gender", length = 10)
    private String gender;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "active")
    private Boolean active = true;
    
    @OneToMany(mappedBy = "customer")
    private Set<Booking> bookings = new LinkedHashSet<>();
    
    
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
    
    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", account=" + account + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", gender='" + gender + '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", active=" + active + '}';
    }
}
