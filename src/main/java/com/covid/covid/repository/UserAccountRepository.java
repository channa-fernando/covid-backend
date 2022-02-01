package com.covid.covid.repository;

import com.covid.covid.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
    @Query("SELECT u FROM UserAccount u WHERE u.email =?1")
    UserAccount findUseByEmail(String email);

    @Query("SELECT u FROM UserAccount u WHERE u.token =?1")
    UserAccount findUseByToken(String token);
}
