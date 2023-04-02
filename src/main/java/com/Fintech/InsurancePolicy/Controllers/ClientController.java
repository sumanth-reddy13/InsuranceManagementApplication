package com.Fintech.InsurancePolicy.Controllers;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    //API for adding client
    @PostMapping("/clients")
    public String addClient(@RequestBody Client client){
        try {
            return clientService.addClient(client);
        }catch (Exception e){
            String response = "client not added";
            return response;
        }
    }

    // API to fetch specific client by id
    @GetMapping("/clients/{id}")
    public Client getClientById(@PathVariable int id){
        return clientService.getClientById(id);
    }


    @GetMapping("/get_all_clients")
    //API to fetch all clients
    public List<Client> getAllClients(){
        return clientService.getAllClients();
    }

    //API to delete a client

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable int id) {
        try {
            return clientService.deleteClient(id);
        } catch (Exception e) {
            String response = "Client is not deleted";
            return response;
        }
    }
}
