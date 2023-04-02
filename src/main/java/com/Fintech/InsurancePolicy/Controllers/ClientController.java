package com.Fintech.InsurancePolicy.Controllers;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.ResponseDto.GetAllClientsResponseDto;
import com.Fintech.InsurancePolicy.Services.ClientService;
import com.Fintech.InsurancePolicy.UpdateDTOs.UpdateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceUnit;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    ClientService clientService;

    //API for adding client
    @PostMapping("/clients")
    public ResponseEntity<String> addClient(@RequestBody Client client){
        String response = "";
        try {
            response =  clientService.addClient(client);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e) {
            response = "client not added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // API to fetch specific client by id
    @GetMapping("/clients/{id}")
    public ResponseEntity<GetAllClientsResponseDto> getClientById(@PathVariable int id)  {
        try {
            GetAllClientsResponseDto clientsResponseDto = clientService.getClientById(id);
            return new ResponseEntity<>(clientsResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_clients")
    //API to fetch all clients
    public ResponseEntity<List<GetAllClientsResponseDto>> getAllClients(){
        List<GetAllClientsResponseDto> list = clientService.getAllClients();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    //API to delete a client

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable int id) {
        String response = "";
        try {
            response = clientService.deleteClient(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = "Client is not deleted";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // Write API to update Client's Information.
    @PutMapping("/updateClient")
    public ResponseEntity<String> updateClient(@RequestBody UpdateClient updateClient) {
        try {
            String response = clientService.updateClient(updateClient);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
