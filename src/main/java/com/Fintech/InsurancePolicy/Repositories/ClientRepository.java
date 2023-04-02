package com.Fintech.InsurancePolicy.Repositories;

import com.Fintech.InsurancePolicy.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Modifying
    @Query(value = "UPDATE clients SET name=:name, address=:address, contact_info=:mobile, " +
            "date_of_birth=:dateOfBirth WHERE id=:id", nativeQuery = true)
    public int updateClient(int id, String name, String mobile, String address, LocalDate dateOfBirth);

}
