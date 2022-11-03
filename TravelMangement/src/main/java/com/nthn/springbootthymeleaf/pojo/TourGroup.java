package com.nthn.springbootthymeleaf.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.springframework.boot.autoconfigure.web.servlet.DefaultJerseyApplicationPath;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tour_group")
public class TourGroup implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "link_static", nullable = false)
    private String linkStatic;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @OneToMany
    @JoinColumn(name = "tour_group_id")
    @ToString.Exclude
    private List<Tour> tours = new ArrayList<>();


    @OneToMany(mappedBy = "tourGroup", orphanRemoval = true)
    private List<News> news = new ArrayList<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourGroup tourGroup = (TourGroup) o;
        return id != null && Objects.equals(id, tourGroup.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
