package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Tour;
import com.nthn.springbootthymeleaf.entity.TourGroup;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>,
		JpaSpecificationExecutor<Tour> {
	
	@Query("select t from Tour t where t.tourGroup.id = ?1")
	List<Tour> findByTourGroupId(Integer id);
	
	@Query("select t from Tour t where t.duration =?1")
	List<Tour> findByDuration(String duration);
	
	@Query("select t from Tour t where t.tourGroup.linkStatic = ?1")
	Page<Tour> findByTourGroup(String tourGroupLink, Pageable pageable);
	
	@Query("select t from Tour t where t.tourGroup.category.linkStatic = ?1")
	Page<Tour> findByCategory(String categoryLink, Pageable pageable);
	
	@Query("select t from Tour t where t.tourGroup.id = ?1")
	Page<Tour> findByTourGroupId(Integer tourGroupId, Pageable pageable);
	
	@Query(
			"select distinct t from Tour t join t.departureDates d where t.departurePlace like ?1 and t"
					+ ".destination" + " like ?2 and d.availableSlot>=?3")
	Page<Tour> searchTours(String departurePlace, String destinationPlace, Integer numberPeople,
			Pageable pageable);
	
	@Query(
			"select distinct t from Tour t join t.departureDates d where t.departurePlace like ?1 and t"
					+ ".destination like ?2 and d.date between ?3 and ?4 and d.availableSlot>=?5")
	Page<Tour> searchTours(String departurePlace, String destinationPlace, LocalDate fromDate,
			LocalDate toDate, Integer slot, Pageable pageable);
	
	@Query(
			"select distinct t from Tour t join t.departureDates d where t.departurePlace like ?1 and t"
					+ ".destination like ?2 and d.date between ?3 and ?4 and d.availableSlot>=?5 and t.unitPrice "
					+ "between " + "?6 and ?7")
	Page<Tour> searchTours(String departurePlace, String destinationPlace, LocalDate fromDate,
			LocalDate toDate, Integer slot, BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);
	
	@Query(
			" select distinct t from Tour t join t.departureDates d where t.departurePlace like ?1 and t.destination "
					+ "like ?2 and d.availableSlot>=?3 and t.unitPrice between ?4 and ?5")
	Page<Tour> searchTours(String departurePlace, String destinationPlace, Integer slot,
			BigDecimal fromPrice, BigDecimal toPrice, Pageable pageable);
	
	@Query("select t from Tour t where t.tourGroup.category.linkStatic = ?1")
	Page<Tour> getByTourGroupCategoryLinkStatic(String categoryLinkStatic, Pageable pageable);
	
	@Query("select t from Tour t where t.tourGroup in ?1")
	Page<Tour> getByTourGroupIn(Set<TourGroup> tourGroups, Pageable pageable);
	
	@Query("select t from Tour t where t.name like '%?1%'")
	Tour findByName(String name);
}