package com.nthn.springbootthymeleaf.pojo;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tour_info")
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

    @Transient
    private String duration;

    public String getDuration() {
        return days + " ngày " + nights + " đêm";
    }

    public void setDuration() {
        this.duration = days + " ngày " + nights + " đêm";
    }

    @Column(name = "days", nullable = false)
    private Integer days;
    @Column(name = "nights", nullable = false)
    private Integer nights;


    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "max_slot", nullable = false)
    private Integer maxSlot;

    @Transient
    public Integer getAvailableSlot() {
        final int[] count = {0};
        bookings.forEach(booking -> {
            booking.getBookingDetails().forEach(detail -> {
                count[0] += detail.getQuantity();
            });
        });
        count[0] = maxSlot - count[0];
        return count[0];
    }

    @Column(name = "transfer", nullable = false)
    private String transfer;

    @Column(name = "departure_date", nullable = true)
    private LocalDate departureDate;

    @Transient
    private String date;


    @Column(name = "departure_place", nullable = false)
    private String departurePlace;
    @Column(name = "destination_place", nullable = false)
    private String destinationPlace;

    @Column(name = "image")
    private String image;

    @Transient
    private MultipartFile multipartFile;

    @Column(name = "active")
    private Boolean active = true;

    @Transient
    public String getPhotosImagePath() {
        if (image == null || id == null) return null;

        return "/tour/" + id + "/" + image;
    }

    @Column(name = "description")
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_group_id", nullable = false)
    @ToString.Exclude
    private TourGroup tourGroup;


    @OneToMany(mappedBy = "tour", orphanRemoval = true)
    @ToString.Exclude
    private Set<TourSchedule> tourSchedules = new LinkedHashSet<>();


    @OneToMany(mappedBy = "tour", orphanRemoval = true)
    @ToString.Exclude
    private List<TourTicket> tourTickets = new ArrayList<>();


    @OneToMany(mappedBy = "tour", orphanRemoval = true)
    @ToString.Exclude
    private Set<Booking> bookings = new LinkedHashSet<>();


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
