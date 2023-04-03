package com.Fintech.InsurancePolicy.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;
     private String claimNumber;
     private String description;
     private LocalDate claimDate;
     private String claimStatus;

     @OneToOne
     @JoinColumn
     private InsurancePolicy policy;

     @ManyToOne
     @JoinColumn
     private Client client;

}
