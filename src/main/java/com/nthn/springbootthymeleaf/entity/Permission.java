package com.nthn.springbootthymeleaf.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "permission")
public class Permission implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_id")
    private List<Account> accounts = new ArrayList<>();
    
    @Transient
    private Integer countAccount;
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Permission that = (Permission) o;
        return id != null && Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
