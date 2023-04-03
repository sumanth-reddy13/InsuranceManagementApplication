package com.Fintech.InsurancePolicy.Converters;

import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.ResponseDto.PolicyResponseDto;

public class InsurancePolicyToResponseDto {

    public static PolicyResponseDto policyToResponseDto(InsurancePolicy insurancePolicy) {

        PolicyResponseDto policyResponseDto = PolicyResponseDto.builder().id(insurancePolicy.getId())
                .coverage_amount(insurancePolicy.getCoverage_amount())
                .endDate(insurancePolicy.getEndDate())
                .policyNumber(insurancePolicy.getPolicyNumber()).premium(insurancePolicy.getPremium())
                .startDate(insurancePolicy.getStartDate()).policyType(insurancePolicy.getType()+"")
                .clientId(insurancePolicy.getClient().getId()).build();

        return policyResponseDto;
    }
}
