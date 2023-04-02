package com.Fintech.InsurancePolicy.Models;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String contactInfo;

    // Client and Insurance policy have OneToMany relationship
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<InsurancePolicy> policies = new ArrayList<>();

    // Client and Claim have OneToMany Relationship.
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Claim> claimList = new ArrayList<>();

    public Client() {

    }

    public Client(int id, String name, LocalDate dateOfBirth, String address, String contactInfo) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.contactInfo = contactInfo;
    }

    public List<InsurancePolicy> getPolicies() {
        return policies;
    }

    // getters and setters

    public void setPolicies(List<InsurancePolicy> policies) {
        this.policies = policies;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getContactInfo() {
        return contactInfo;
    }
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Claim> getClaimList() {
        return claimList;
    }

    public void setClaimList(List<Claim> claimList) {
        this.claimList = claimList;
    }
}