package com.Fintech.InsurancePolicy.Controllers;

import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.RequestDTOs.ClientRequestDto;
import com.Fintech.InsurancePolicy.ResponseDto.ClientResponseDto;
import com.Fintech.InsurancePolicy.Services.ClientService;
import com.Fintech.InsurancePolicy.UpdateDTOs.UpdateClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    //API for adding client
    @PostMapping("/addClient")
    public ResponseEntity<String> addClient(@RequestBody ClientRequestDto clientRequestDto){
        String response = "";
        try {
            response =  clientService.addClient(clientRequestDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e) {
            response = "Failed to add the client. Please provide valid details";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // API to fetch specific client by id
    @GetMapping("/getClient/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable int id)  {
        try {
            ClientResponseDto clientsResponseDto = clientService.getClientById(id);
            return new ResponseEntity<>(clientsResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_clients")
    //API to fetch all clients
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        try {
            List<ClientResponseDto> list = clientService.getAllClients();
            return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //API to delete a client

    @DeleteMapping("/deleteClient/{id}")
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
