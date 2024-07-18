package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -1829958074745546587L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "currency", nullable = false, length = 45)
    private String currency;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "method", nullable = false, length = 45)
    private String method;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "intent", nullable = false, length = 45)
    private String intent;
    
    @OneToMany(mappedBy = "payment")
    @ToString.Exclude
    private Set<Booking> bookings = new LinkedHashSet<>();
    
    @Transient
    private Double total;
    
    
    @Size(max = 45)
    @Column(name = "name", length = 45)
    private String name;
}