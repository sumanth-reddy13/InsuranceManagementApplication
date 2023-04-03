package com.Fintech.InsurancePolicy.UpdateDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClient {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String contactInfo;
    private String address;
    private String email;
}

