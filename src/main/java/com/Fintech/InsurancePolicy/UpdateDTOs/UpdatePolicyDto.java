package com.Fintech.InsurancePolicy.UpdateDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePolicyDto {

    // UPDATE policies SET coverage_amount=:amount, end_date:endDate,
    // premium=:premium, start_date=:startDate, type=:insuranceType WHERE id=:policyId;
    private double coverageAmount;
    private LocalDate endDate;
    private double premium;
    private LocalDate startDate;
    private String insuranceType;
    private int policyId;
}
