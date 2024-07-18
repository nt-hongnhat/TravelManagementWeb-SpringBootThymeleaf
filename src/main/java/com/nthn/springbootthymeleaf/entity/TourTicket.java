package com.nthn.springbootthymeleaf.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
@Table(name = "tour_ticket")
public class TourTicket implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Getter
    public static List<GROUP> names;
    
    static {
        names = new ArrayList<>();
        names.add(GROUP.ADULT);
        names.add(GROUP.CHILDREN);
        names.add(GROUP.YOUNGCHILDREN);
        names.add(GROUP.INFANTS);
    }
    
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "tour_id", nullable = false)
    @ToString.Exclude
    private Tour tour;
    
    //    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    //    @JoinColumn(name = "id")
    //    @ToString.Exclude
    //    private Surcharge surcharge;
    
    //    @OneToMany(mappedBy = "tourTicket", cascade = CascadeType.ALL, orphanRemoval = true)
    //    @ToString.Exclude
    //    private Set<BookingDetail> bookingDetails = new LinkedHashSet<>();
    
    @NotNull
    @Column(name = "max_slot", nullable = false)
    private Integer maxSlot;
    
    public TourTicket(@org.jetbrains.annotations.NotNull String name, Tour tour, int maxSlot) {
        this.name = name;
        this.unitPrice = name.equals(GROUP.ADULT.content) ? tour.getUnitPrice() : BigDecimal.valueOf(0);
        this.tour = tour;
        this.maxSlot = maxSlot;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TourTicket that = (TourTicket) o;
        return id != null && Objects.equals(id, that.id);
    }
    
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    
    @Getter
    public enum GROUP {
        ADULT("Người lớn (Trên 11 tuổi)"), CHILDREN("Trẻ em (5 - 11 tuổi)"), YOUNGCHILDREN(
                "Trẻ nhỏ (2 - 5 tuổi)"), INFANTS("Sơ sinh (<2 tuổi)");
        
        final String content;
        
        GROUP(String content) {this.content = content;}
        
        @Override
        public String toString() {
            return content;
        }
    }
}
