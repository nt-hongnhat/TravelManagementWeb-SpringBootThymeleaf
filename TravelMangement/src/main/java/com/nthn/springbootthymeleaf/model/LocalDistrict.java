package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "local_district")
public class LocalDistrict {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @ManyToOne
    @JoinColumn(name = "province_id")
    private LocalProvince province;

}
