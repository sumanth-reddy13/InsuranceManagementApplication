package com.Fintech.InsurancePolicy.Models;

import com.Fintech.InsurancePolicy.Enums.PolicyType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String policyNumber;
    @Enumerated(EnumType.STRING)
    private PolicyType type;
    private double coverage_amount;
    private double premium;
    private LocalDate startDate;
    private LocalDate endDate;


    //relation between insurance and client policy is ManyToOne Relationship.
    @ManyToOne
    @JoinColumn
    private Client client;

    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private List<Claim> claims = new ArrayList<>();

    //default constructor
    public InsurancePolicy() {
    }

    //parameterised constructor

    public InsurancePolicy(int id, String policyNumber, PolicyType type, double coverage_amount, double premium, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.type = type;
        this.coverage_amount = coverage_amount;
        this.premium = premium;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    //getters and setters

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public PolicyType getType() {
        return type;
    }

    public void setType(PolicyType type) {
        this.type = type;
    }

    public double getCoverage_amount() {
        return coverage_amount;
    }

    public void setCoverage_amount(double coverage_amount) {
        this.coverage_amount = coverage_amount;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
