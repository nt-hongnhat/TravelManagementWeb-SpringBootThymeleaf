package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 5738832513384736017L;
    
    @Id
    @NotNull
    @Column(name = "id", nullable = false)
    private Integer id;
    
    @NotNull
    @Column(name = "account_id", nullable = false)
    private Integer accountId;
    
    @Size(max = 15)
    @NotNull
    @Column(name = "identity", nullable = false, length = 15)
    private String identityNumber;
    
    @Size(max = 10)
    @NotNull
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;
    
    @NotNull
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    
    @Size(max = 255)
    @Column(name = "address")
    private String address;
    
    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;
    
    @Column(name = "active")
    private Boolean active;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
    
    @Size(max = 45)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
    
    @OneToMany(mappedBy = "employee")
    @ToString.Exclude
    private List<WorkFor> workFors = new ArrayList<>();
    
}