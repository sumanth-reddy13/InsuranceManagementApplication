package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.DTOs.InsurancePolicyRequestDto;
import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import com.Fintech.InsurancePolicy.Repositories.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsurancePolicyService {

    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    ClientRepository clientRepository;

    public String addInsurancePolicy(InsurancePolicyRequestDto insurancePolicyRequestDto){

        int clientId = insurancePolicyRequestDto.getClientId();
        Client client = clientRepository.findById(clientId).get();

        InsurancePolicy insurancePolicy = new InsurancePolicy();
        insurancePolicy.setPolicyNumber(insurancePolicyRequestDto.getPolicyNumber());
        insurancePolicy.setType(insurancePolicyRequestDto.getType());
        insurancePolicy.setPremium(insurancePolicyRequestDto.getPremium());
        insurancePolicy.setCoverage_amount(insurancePolicyRequestDto.getCoverage_amount());
        insurancePolicy.setStartDate(insurancePolicyRequestDto.getStartDate());
        insurancePolicy.setEndDate(insurancePolicyRequestDto.getEndDate());

        //setting foreign key
        insurancePolicy.setClient(client);

        List<InsurancePolicy> insurancePolicyList = client.getPolicies();
        insurancePolicyList.add(insurancePolicy);

        clientRepository.save(client);
        return "Policy added successfully";
    }

    public InsurancePolicy getPolicyById(int id){
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();
        return insurancePolicy;
    }

    public List<InsurancePolicy> getAllPolicies(){
        List<InsurancePolicy> insurancePolicyList = insurancePolicyRepository.findAll();
        return insurancePolicyList;
    }

    public String deletePolicy(int id){
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();
        insurancePolicyRepository.delete(insurancePolicy);
        return "policy deleted successfully";
    }
}