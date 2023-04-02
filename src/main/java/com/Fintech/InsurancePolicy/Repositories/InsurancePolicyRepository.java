package com.Fintech.InsurancePolicy.Repositories;

import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Integer> {
}
