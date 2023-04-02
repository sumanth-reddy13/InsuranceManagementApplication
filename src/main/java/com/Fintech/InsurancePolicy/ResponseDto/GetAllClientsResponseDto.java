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
public class GetAllClientsResponseDto {
    String name;
    LocalDate dateOfBirth;
    String address;
    String mobileNumber;
}
