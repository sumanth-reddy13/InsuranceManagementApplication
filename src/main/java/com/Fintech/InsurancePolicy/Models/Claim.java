package com.Fintech.InsurancePolicy.Models;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "claims")
public class Claim {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private int id;

     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private String claimNumber;
     private String description;
     private LocalDate claimDate;
     private String claimStatus;

     @ManyToOne
     @JoinColumn
     private InsurancePolicy policy;

     public Claim() {
     }

     public Claim(int id, String claimNumber, String description, LocalDate claimDate, String claimStatus, InsurancePolicy policy) {
         this.id = id;
         this.claimNumber = claimNumber;
         this.description = description;
         this.claimDate = claimDate;
         this.claimStatus = claimStatus;
         this.policy = policy;
     }

     public int getId() {
         return id;
     }

     public void setId(int id) {
         this.id = id;
     }

     public String getClaimNumber() {
         return claimNumber;
     }

     public void setClaimNumber(String claimNumber) {
         this.claimNumber = claimNumber;
     }

     public String getDescription() {
         return description;
     }

     public void setDescription(String description) {
         this.description = description;
     }

     public LocalDate getClaimDate() {
         return claimDate;
     }

     public void setClaimDate(LocalDate claimDate) {
         this.claimDate = claimDate;
     }

     public String getClaimStatus() {
         return claimStatus;
     }

     public void setClaimStatus(String claimStatus) {
         this.claimStatus = claimStatus;
     }

     public InsurancePolicy getPolicy() {
         return policy;
     }

     public void setPolicy(InsurancePolicy policy) {
         this.policy = policy;
     }
}
