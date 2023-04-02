package com.Fintech.InsurancePolicy.Repositories;

import com.Fintech.InsurancePolicy.Enums.PolicyType;
import com.Fintech.InsurancePolicy.Models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.time.LocalDate;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Integer> {

    @Modifying
    @Query(value = "UPDATE claims SET claim_status=:claimStatus, claim_date=:date, description=:description WHERE id=:id", nativeQuery = true)
    public int updateClaim(int id, String description, LocalDate date, String claimStatus);
}
