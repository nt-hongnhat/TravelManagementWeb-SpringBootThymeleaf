package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.pojo.Tour;
import com.nthn.springbootthymeleaf.pojo.TourGroup;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface TourRepository extends JpaRepository<Tour, Integer>, JpaSpecificationExecutor<Tour> {

    @Query("select t from Tour t where t.tourGroup.id = ?1")
    List<Tour> findByTourGroupId(Integer id);

    @Query("select t from Tour t where t.duration = ?1")
    List<Tour> findByDuration(String duration);

    @Query("select t from Tour t where t.tourGroup.linkStatic = ?1")
    Page<Tour> findByTourGroup(String tourGroupLink, Pageable pageable);

    @Query("select t from Tour t where t.tourGroup.category.linkStatic = ?1")
    Page<Tour> findByCategory(String categoryLink, Pageable pageable);

//    //
////    @Query("""
////            select t from Tour t
////            where t.departurePlace = ?1 and t.destinationPlace = ?2 and t.departureDate between ?3 and ?4""")
////    Page<Tour> searchToursByDeparturePlaceAndDestinationPlaceAndDepartureDateBetween(String departurePlace, String destinationPlace, Date fromDate, Date toDate, Pageable pageable);
//    @Query("""
//            select t from Tour t
//            where t.departurePlace = ?1 and t.destinationPlace = ?2 and t.duration = ?3 and t.maxSlot >= ?4""")
//    Page<Tour> searchTours(String departurePlace, String destinationPlace, String duration, Integer availableSlot, Pageable pageable);


    /**
     * T&igrave;m ki&#x1EBF;m tour
     *
     * @param fromPlace n&#x1A1;i kh&#x1EDF;i h&agrave;nh
     * @param toPlace   n&#x1A1;i &#x111;&#x1EBF;n
     * @param fromDate  ng&agrave;y kh&#x1EDF;i h&agrave;nh t&#x1EEB; ng&agrave;y
     * @param toDate    ng&agrave;y kh&#x1EDF;i h&agrave;nh &#x111;&#x1EBF;n ng&agrave;y
     * @param fromPrice gi&aacute; t&#x1EEB;
     * @param toPrice   &#x111;&#x1EBF;n gi&aacute;
     * @param pageable  page, size
     * @return page tour
     */
    @Query("""
            select t from Tour t
            where t.departurePlace = ?1 and t.destinationPlace = ?2
            and t.departureDate between ?3 and ?4 and t.unitPrice between ?5 and ?6""")
    Page<Tour> searchTours(String fromPlace, String toPlace, LocalDate fromDate, LocalDate toDate,
                           BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);

    /**
     * T&igrave;m ki&#x1EBF;m tour
     *
     * @param fromPlace n&#x1A1;i kh&#x1EDF;i h&agrave;nh
     * @param toPlace   n&#x1A1;i &#x111;&#x1EBF;n
     * @param fromDate  ng&agrave;y kh&#x1EDF;i h&agrave;nh t&#x1EEB; ng&agrave;y
     * @param toDate    ng&agrave;y kh&#x1EDF;i h&agrave;nh &#x111;&#x1EBF;n ng&agrave;y
     * @param pageable  page, size
     * @return page tour
     */
    @Query("""
            select t from Tour t
            where t.departurePlace = ?1 and t.destinationPlace = ?2 and t.departureDate between ?3 and ?4""")
    Page<Tour> searchTours(String fromPlace, String toPlace, LocalDate fromDate, LocalDate toDate, Pageable pageable);


//    @Query("""
//            select t from Tour t
//            where t.departurePlace = ?1 and t.destinationPlace = ?2 and t.duration = ?3 and t.tourGroup.linkStatic = ?4""")
////    Page<Tour> searchToursByDeparturePlaceAndDestinationPlaceAndDurationAndTourGroupLinkStatic(String departurePlace, String destinationPlace, String duration, Pageable pageable);
//    Page<Tour> searchTours(String departurePlace, String destinationPlace, String duration, String tourGroupLink, Pageable pageable);

    @Query("select t from Tour t where t.departureDate between ?1 and ?2")
    Page<Tour> findByDepartureDateBetween(LocalDateTime fromDate, LocalDateTime toDate,
                                          Pageable pageable);

    @Query("select t from Tour t where t.tourGroup.category.linkStatic = ?1")
    Page<Tour> getByTourGroupCategoryLinkStatic(String categoryLinkStatic, Pageable pageable);

    @Query("select t from Tour t where t.tourGroup in ?1")
    Page<Tour> getByTourGroupIn(Set<TourGroup> tourGroups, Pageable pageable);
}