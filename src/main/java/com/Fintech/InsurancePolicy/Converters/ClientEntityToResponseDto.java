package com.Fintech.InsurancePolicy.Converters;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.ResponseDto.ClientResponseDto;

public class ClientEntityToResponseDto {

    public static ClientResponseDto entityToResponseDto(Client client) {
        ClientResponseDto clientDto = ClientResponseDto.builder()
                .clientId(client.getId())
                .name(client.getName())
                .dateOfBirth(client.getDateOfBirth())
                .mobileNumber(client.getContactInfo())
                .address(client.getAddress()).build();

        return clientDto;
    }
}
