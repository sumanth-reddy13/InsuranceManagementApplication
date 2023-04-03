package com.Fintech.InsurancePolicy.RequestDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceClaimDto {

    private int policyId;
    private int clientId;
    private double amountToClaim;
    private String description;

}