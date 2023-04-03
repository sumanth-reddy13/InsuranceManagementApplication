package com.Fintech.InsurancePolicy.Converters;

import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.RequestDTOs.InsurancePolicyRequestDto;

public class RequestDtoToInsurancePolicyEntity {

    public static InsurancePolicy requestDtoToPolicyEntity(InsurancePolicyRequestDto insurancePolicyRequestDto) {
        InsurancePolicy insurancePolicy = new InsurancePolicy();

        insurancePolicy.setPolicyNumber(insurancePolicyRequestDto.getPolicyNumber());
        insurancePolicy.setType(insurancePolicyRequestDto.getType());
        insurancePolicy.setPremium(insurancePolicyRequestDto.getPremium());
        insurancePolicy.setCoverage_amount(insurancePolicyRequestDto.getCoverage_amount());
        insurancePolicy.setStartDate(insurancePolicyRequestDto.getStartDate());
        insurancePolicy.setEndDate(insurancePolicyRequestDto.getEndDate());

        return insurancePolicy;
    }
}
