package com.nthn.springbootthymeleaf.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tour_info", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class Tour implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "itinerary", nullable = false)
    private String itinerary;
    
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    
    @Column(name = "transfer", nullable = false)
    private String transfer;
    
    @Column(name = "departure_place", nullable = false)
    private String departurePlace;
    
    @Column(name = "destination", nullable = false)
    private String destination;
    
    @Column(name = "image")
    private String image;
    
    @Transient
    private MultipartFile multipartFile;
    
    @Column(name = "active")
    private Boolean active = true;
    
    @Column(name = "description")
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_group_id", nullable = false)
    @ToString.Exclude
    private TourGroup tourGroup;
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<TourSchedule> tourSchedules = new ArrayList<>();
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<TourTicket> tourTickets = new ArrayList<>();
    
    @OneToMany(mappedBy = "tour", fetch = FetchType.EAGER)
    private List<DepartureDate> departureDates = new ArrayList<>();
    
    @Size(max = 45)
    @NotNull
    @Column(name = "duration", nullable = false, length = 45)
    private String duration;
    
    @Transient
    private int availableSlot = 0;
    
    @Column(name = "max_slot")
    private Integer maxSlot;
    
    @Transient
    private String thumbnail;
    
    public String getThumbnail() {
        thumbnail = image == null || image.isEmpty() ? "/img/default_image.jpg" :
                Arrays.stream(image.split(" ")).toList().get(0);
        return thumbnail;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tour tour = (Tour) o;
        return id != null && Objects.equals(id, tour.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    
}
