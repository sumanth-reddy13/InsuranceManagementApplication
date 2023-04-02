package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import com.Fintech.InsurancePolicy.ResponseDto.GetAllClientsResponseDto;
import com.Fintech.InsurancePolicy.UpdateDTOs.UpdateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;


    public String addClient(Client client){
        clientRepository.save(client);
        return "Client added successfully";
    }

    public GetAllClientsResponseDto getClientById(int id) throws Exception{

        Client client = null;
        try {
            client = clientRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new Exception("Requested Id not present");
        }

        GetAllClientsResponseDto clientDto = GetAllClientsResponseDto.builder()
                                            .name(client.getName())
                                            .dateOfBirth(client.getDateOfBirth())
                                            .mobileNumber(client.getContactInfo())
                                            .address(client.getAddress()).build();


        return clientDto;
    }

    public List<GetAllClientsResponseDto> getAllClients(){
        List<Client> clientsList = clientRepository.findAll();

        List<GetAllClientsResponseDto> list = new ArrayList<>();
        for (Client client : clientsList) {
            GetAllClientsResponseDto clientList = GetAllClientsResponseDto.builder()
                                                    .name(client.getName())
                                                    .dateOfBirth(client.getDateOfBirth())
                                                    .mobileNumber(client.getContactInfo())
                                                    .address(client.getAddress())
                                                    .build();

            list.add(clientList);
        }
        return list;
    }

    public String deleteClient(int id){
        Client client = clientRepository.findById(id).get();
        clientRepository.delete(client);
        return "Client deleted Successfully";
    }

    @Transactional
    public String updateClient(UpdateClient updateClient) {

        int clientId = updateClient.getId();
        String name = updateClient.getName();
        LocalDate dateOfBirth = updateClient.getDateOfBirth();
        String mobile = updateClient.getContactInfo();
        String address = updateClient.getAddress();

        int rowsUpdated = clientRepository.updateClient(clientId, name, mobile, address, dateOfBirth);
        if (rowsUpdated == 0) {
            return "provide valid details";
        }
        return rowsUpdated + " rows updated";
    }
}