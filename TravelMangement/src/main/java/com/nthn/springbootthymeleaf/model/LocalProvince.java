package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "local_province")
public class LocalProvince {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

}
