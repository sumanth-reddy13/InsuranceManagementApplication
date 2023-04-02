package com.Fintech.InsurancePolicy.UpdateDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimUpdateDto {

    private int claimId;
    private LocalDate claimDate;
    private String claimStatus;
    private String description;
}
