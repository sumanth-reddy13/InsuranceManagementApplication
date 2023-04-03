package com.Fintech.InsurancePolicy.Models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    @Column(name = "contactInfo", unique = true)
    private String contactInfo;
    @Column(name = "email", unique = true)
    private String emailId;


    // Client and Insurance policy have OneToMany relationship
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<InsurancePolicy> policies = new ArrayList<>();

    // Client and Claim have OneToMany Relationship.
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Claim> claimList = new ArrayList<>();

}