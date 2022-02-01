package com.covid.covid.repository;

import com.covid.covid.entity.LocationDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDetailRepository extends JpaRepository<LocationDetail, Long> {
}
