package com.Fintech.InsurancePolicy.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClaimResponseDto {
    // id | claim_date | claim_number          | claim_status | description        | client_id | policy_id
    int id;
    LocalDate claimDate;
    String claimNumber;
    String claimStatus;
    String description;
    int clientId;
    int policyId;

}
