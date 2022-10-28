package com.nthn.springbootthymeleaf.pojo;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "category")
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "link_static", nullable = false)
    private String linkStatic;


    @Column(name = "description")
    private String description;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private List<TourGroup> tourGroups = new ArrayList<>();


}
