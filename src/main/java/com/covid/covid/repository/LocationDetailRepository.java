package com.covid.covid.repository;

import com.covid.covid.entity.LocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDetailRepository extends JpaRepository<LocationDetail, Long> {
    @Query("SELECT ld FROM LocationDetail ld WHERE ld.fireBaseSavedTime is null")
    List<LocationDetail> unsavedLocationDetails();
}
