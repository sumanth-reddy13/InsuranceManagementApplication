package com.Fintech.InsurancePolicy.Models;

import com.Fintech.InsurancePolicy.Enums.PolicyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "policies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsurancePolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "policyNumber", unique = true)
    private String policyNumber;
    @Enumerated(EnumType.STRING)
    private PolicyType type;
    private double coverage_amount;
    private double premium;
    private LocalDate startDate;
    private LocalDate endDate;


    // Relation between insurance and client policy is ManyToOne Relationship.
    @ManyToOne
    @JoinColumn
    private Client client;

    // Relation between InsurancePolicy and Claim is OneToOne
    @OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
    private List<Claim> insuranceClaims = new ArrayList<>();

}
