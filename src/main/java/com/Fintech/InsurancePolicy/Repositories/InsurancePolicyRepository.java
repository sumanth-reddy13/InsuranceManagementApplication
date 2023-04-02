package com.Fintech.InsurancePolicy.Repositories;

import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {

    @Modifying
    @Query(value = "UPDATE policies SET coverage_amount=:amount, end_date=:endDate, premium=:premium," +
            " start_date=:startDate, type=:insuranceType WHERE id=:policyId", nativeQuery = true)
    public int updatePolicy(double amount, LocalDate endDate, double premium, LocalDate startDate,
                            String insuranceType, int policyId);

}
