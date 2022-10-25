package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {

    @Query("select t from Tour t where t.tourGroup.id = ?1")
    List<Tour> findByTourGroupId(Integer id);

    @Query("select t from Tour t where t.duration = ?1")
    List<Tour> findByDuration(String duration);

    @Query("select t from Tour t where t.tourGroup.linkStatic = ?1")
    Page<Tour> findByTourGroup(@NotNull Pageable pageable, String linkStatic);
//
//    @Query("""
//            select t from Tour t
//            where t.departurePlace = ?1 and t.destinationPlace = ?2 and t.departureDate between ?3 and ?4""")
//    Page<Tour> searchToursByDeparturePlaceAndDestinationPlaceAndDepartureDateBetween(String departurePlace, String destinationPlace, Date fromDate, Date toDate, Pageable pageable);

    @Query("""
            select t from Tour t
            where t.departurePlace = ?1 and t.destinationPlace = ?2 and t.duration = ?3 and t.tourGroup.linkStatic = ?4""")
//    Page<Tour> searchToursByDeparturePlaceAndDestinationPlaceAndDurationAndTourGroupLinkStatic(String departurePlace, String destinationPlace, String duration, Pageable pageable);
    Page<Tour> searchTours(String departurePlace, String destinationPlace, String duration, String tourGroupLink, Pageable pageable);

    @Query("select t from Tour t where t.departureDate between ?1 and ?2")
    Page<Tour> findByDepartureDateBetween(LocalDateTime fromDate, LocalDateTime toDate,
                                          Pageable pageable);

    @Query("select t from Tour t where t.tourGroup.category.linkStatic = ?1")
    Page<Tour> getByTourGroupCategoryLinkStatic(String categoryLinkStatic, Pageable pageable);

    @Query("select t from Tour t where t.tourGroup in ?1")
    Page<Tour> getByTourGroupIn(List<TourGroup> tourGroups, Pageable pageable);

}