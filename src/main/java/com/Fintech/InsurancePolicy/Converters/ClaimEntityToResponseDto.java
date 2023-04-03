package com.Fintech.InsurancePolicy.Converters;

import com.Fintech.InsurancePolicy.Models.Claim;
import com.Fintech.InsurancePolicy.ResponseDto.ClaimResponseDto;
import com.Fintech.InsurancePolicy.ResponseDto.ClientResponseDto;

public class ClaimEntityToResponseDto {

    public static ClaimResponseDto entityToResponseDto(Claim claim) {

        ClaimResponseDto claimResponseDto = ClaimResponseDto.builder().id(claim.getId())
                .claimDate(claim.getClaimDate()).claimStatus(claim.getClaimStatus())
                .claimNumber(claim.getClaimNumber()).description(claim.getDescription())
                .policyId(claim.getPolicy().getId())
                .clientId(claim.getClient().getId()).build();

        return claimResponseDto;
    }
}
