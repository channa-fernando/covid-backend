package com.covid.covid.repository;

import com.covid.covid.entity.ContactTracingDetail;
import com.covid.covid.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactTracingDetailRepository extends JpaRepository<ContactTracingDetail, Long> {

    Optional<ContactTracingDetail> findContactTracingDetailByUserAccountId(Long id);

    @Query("SELECT u FROM ContactTracingDetail u WHERE u.userAccount.role =?1")
    List<ContactTracingDetail> findUseByUserRole(String role);
}
