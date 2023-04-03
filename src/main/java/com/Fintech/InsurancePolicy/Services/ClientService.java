package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.Converters.ClientEntityToResponseDto;
import com.Fintech.InsurancePolicy.Converters.RequestDtoToClientEntity;
import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import com.Fintech.InsurancePolicy.RequestDTOs.ClientRequestDto;
import com.Fintech.InsurancePolicy.ResponseDto.ClientResponseDto;
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


    public String addClient(ClientRequestDto clientRequestDto) throws Exception {

        // calls a converter function to convert the DTO to a Client Entity.
        Client client = RequestDtoToClientEntity.requestDtoToClient(clientRequestDto);

        client = clientRepository.save(client);

        int clientId = client.getId();
        if (!(clientId > 0)) {
            return "failed to add the client";
        }

        return "Client added successfully. Client's unique Id is " + clientId;
    }

    public ClientResponseDto getClientById(int id) throws Exception {

        Client client = null;
        try {
            client = clientRepository.findById(id).get();
        }
        catch (Exception e) {
            throw new Exception("Requested Id not present");
        }

        // calls a converter function that converts the Entity to Response DTO
        ClientResponseDto clientDto = ClientEntityToResponseDto.entityToResponseDto(client);

        return clientDto;
    }

    public List<ClientResponseDto> getAllClients()  throws Exception    {
        List<Client> clientsList = clientRepository.findAll();

        List<ClientResponseDto> list = new ArrayList<>();
        for (Client client : clientsList) {

            ClientResponseDto clientList = ClientEntityToResponseDto.entityToResponseDto(client);  // calling converter function.
            list.add(clientList);
        }
        return list;
    }

    public String deleteClient(int id)  throws Exception {
        Client client = clientRepository.findById(id).get();
        clientRepository.delete(client);
        return "Client deleted Successfully";
    }

    // Used Transactional Annotation to ensure that data is secured (rolled back to its original state) in case of any failure.
    @Transactional
    public String updateClient(UpdateClient updateClient) throws Exception  {

        int clientId = updateClient.getId();
        String name = updateClient.getName();
        LocalDate dateOfBirth = updateClient.getDateOfBirth();
        String mobile = updateClient.getContactInfo();
        String address = updateClient.getAddress();
        String email = updateClient.getEmail();

        // A call to a updateClient method in clientRepository to update the client details.
        int rowsUpdated = clientRepository.updateClient(clientId, name, mobile, address, dateOfBirth, email);

        if (rowsUpdated == 0) {
            return "provide valid details";
        }

        return rowsUpdated + " rows updated";
    }
}