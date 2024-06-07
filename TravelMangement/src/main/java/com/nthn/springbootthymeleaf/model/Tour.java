package com.nthn.springbootthymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @Column(name = "id", length = -1)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "name")
    private String name;

    @Column(name = "itinerary")
    private String itinerary;

    @Column(name = "duration", length = 45)
    private String duration;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "transfer")
    private String transfer;

    @Column(name = "place_of_departure", length = 45)
    private String placeOfDeparture;

    @Column(name = "destination", length = 45)
    private String destination;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

}
