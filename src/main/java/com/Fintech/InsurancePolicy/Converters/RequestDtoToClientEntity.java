package com.Fintech.InsurancePolicy.Converters;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.RequestDTOs.ClientRequestDto;

public class RequestDtoToClientEntity {

    public static Client requestDtoToClient(ClientRequestDto clientRequestDto) {
        Client client = new Client();

        client.setName(clientRequestDto.getName());
        client.setContactInfo(clientRequestDto.getContactInfo());
        client.setAddress(clientRequestDto.getAddress());
        client.setEmailId(clientRequestDto.getEmail());
        client.setDateOfBirth(clientRequestDto.getDateOfBirth());

        return client;
    }


}
