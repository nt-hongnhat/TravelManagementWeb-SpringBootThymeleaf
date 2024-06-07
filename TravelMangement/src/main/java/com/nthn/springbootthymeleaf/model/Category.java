package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category  implements Serializable {
    @Serial
    private static final long serialVersionUID = 456557682097373817L;
    @Id
    @Column(name = "id", length = -1)
    private Integer id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "url", length = 45)
    private String url;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Group> groups= new ArrayList<>();

}
