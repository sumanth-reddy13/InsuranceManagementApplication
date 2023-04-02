package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;


    public String addClient(Client client){
        clientRepository.save(client);
        return "Client added successfully";
    }

    public Client getClientById(int id){
        Client client = clientRepository.findById(id).get();
        return client;
    }

    public List<Client> getAllClients(){
        List<Client> clientsList = clientRepository.findAll();
        return clientsList;
    }

    public String deleteClient(int id){
        Client client = clientRepository.findById(id).get();
        clientRepository.delete(client);
        return "Client deleted Successfully";
    }
}