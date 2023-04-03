package com.Fintech.InsurancePolicy.RequestDTOs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {
    String name;
    String contactInfo;
    LocalDate dateOfBirth;
    String email;
    String address;
}
//"name" : "Rohit Sharma",
//        "dateOfBirth" : "1986-06-19",
//        "address" : "Mumbai",
//        "contactInfo" : "9863251470",
//        "email"
