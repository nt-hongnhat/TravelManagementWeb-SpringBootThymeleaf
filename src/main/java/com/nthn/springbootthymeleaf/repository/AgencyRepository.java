package com.nthn.springbootthymeleaf.repository;

import com.nthn.springbootthymeleaf.entity.Agency;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<Agency, Integer>, JpaSpecificationExecutor<Agency> {

    @Override
    @Query("select a from Agency a")
    @NotNull
    Page<Agency> findAll(@NotNull Pageable pageable);

    @Query("select a from Agency a where upper(a.name) like upper(concat('%', ?1, '%'))")
    Page<Agency> findAllByNameContainingIgnoreCase(@NotNull String name, @NotNull Pageable pageable);

    @Query("select a from Agency a where upper(a.address) like upper(concat('%', ?1, '%'))")
    Page<Agency> findByAddressContainingIgnoreCase(String address, Pageable pageable);

    @Query("select a from Agency a where upper(a.name) like upper(concat('%', ?1, '%')) order by a.name")
    List<Agency> findByNameContainingIgnoreCaseOrderByName(String name);

}