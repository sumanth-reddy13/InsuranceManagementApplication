package com.Fintech.InsurancePolicy.Repositories;

import com.Fintech.InsurancePolicy.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
