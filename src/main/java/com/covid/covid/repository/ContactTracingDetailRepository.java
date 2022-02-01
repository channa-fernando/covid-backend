package com.covid.covid.repository;

import com.covid.covid.entity.ContactTracingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTracingDetailRepository extends JpaRepository<ContactTracingDetail, Long> {
}
