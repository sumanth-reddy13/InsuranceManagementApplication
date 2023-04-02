package com.Fintech.InsurancePolicy.Repositories;

import com.Fintech.InsurancePolicy.Models.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Integer> {
}
