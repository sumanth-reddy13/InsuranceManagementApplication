package com.Fintech.InsurancePolicy.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PolicyResponseDto {

    private int id;
    private double coverage_amount;
    private LocalDate endDate;
    private LocalDate startDate;
    private String policyNumber;
    private double premium;
    private String policyType;
    private int clientId;
}
