package com.nthn.springbootthymeleaf.model;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class Schedule {
    @Id
    @Column(name = "id", length = -1)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    private Tour tour;

    @Column(name = "ordinal_date")
    private Integer ordinalDate;

    @Column(name = "itinerary")
    private String itinerary;

    @Column(name = "description")
    private String description;


}
