package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "employee")
@NoArgsConstructor
public class Employee {
    @Id
    @Column(name = "id", length = -1)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "identity", length = 15)
    private String identity;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "birth_date")
    private Timestamp birthDate;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "active")
    private Boolean active;

}
