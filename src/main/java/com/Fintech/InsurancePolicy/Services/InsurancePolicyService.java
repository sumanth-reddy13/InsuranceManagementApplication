package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.Converters.InsurancePolicyToResponseDto;
import com.Fintech.InsurancePolicy.Converters.RequestDtoToInsurancePolicyEntity;
import com.Fintech.InsurancePolicy.RequestDTOs.InsurancePolicyRequestDto;
import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import com.Fintech.InsurancePolicy.Repositories.InsurancePolicyRepository;
import com.Fintech.InsurancePolicy.ResponseDto.PolicyResponseDto;
import com.Fintech.InsurancePolicy.UpdateDTOs.UpdatePolicyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsurancePolicyService {

    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;

    @Autowired
    ClientRepository clientRepository;

    public String addInsurancePolicy(InsurancePolicyRequestDto insurancePolicyRequestDto)   throws Exception   {

        int clientId = insurancePolicyRequestDto.getClientId();
        Client client = clientRepository.findById(clientId).get();

        // Converter to convert from DTO to Entity.
        InsurancePolicy insurancePolicy = RequestDtoToInsurancePolicyEntity.requestDtoToPolicyEntity(insurancePolicyRequestDto);

        //setting foreign key
        insurancePolicy.setClient(client);

        // Adding the policy to the list of client's policies.
        client.getPolicies().add(insurancePolicy);

        insurancePolicy = insurancePolicyRepository.save(insurancePolicy);

        clientRepository.save(client);

        if (!(insurancePolicy.getId() > 0)) return "failed create the policy for the client";

        return "Policy created successfully for " + client.getName() + ". Policy Unique Id is " + insurancePolicy.getId() ;
    }

    public PolicyResponseDto getPolicyById(int id) throws Exception {
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();

        // Converter
        PolicyResponseDto policyResponseDto = InsurancePolicyToResponseDto.policyToResponseDto(insurancePolicy);

        return policyResponseDto;
    }

    public List<PolicyResponseDto> getAllPolicies() throws Exception    {

        List<InsurancePolicy> insurancePolicyList = insurancePolicyRepository.findAll();
        List<PolicyResponseDto> policyResponseList = new ArrayList<>();

        for (InsurancePolicy insurancePolicy : insurancePolicyList) {

            // Converter
            PolicyResponseDto policyResponseDto = InsurancePolicyToResponseDto.policyToResponseDto(insurancePolicy);

            policyResponseList.add(policyResponseDto);
        }

        return policyResponseList;
    }

    public String deletePolicy(int id) throws Exception {

        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();
        insurancePolicyRepository.delete(insurancePolicy);

        return "policy deleted successfully";
    }

    @Transactional
    public String updatePolicy(UpdatePolicyDto updatePolicyDto) throws Exception    {

        double amount = updatePolicyDto.getCoverageAmount();
        LocalDate endDate = updatePolicyDto.getEndDate();
        LocalDate startDate = updatePolicyDto.getStartDate();
        double premium = updatePolicyDto.getPremium();
        String insuranceType = updatePolicyDto.getInsuranceType();
        int id = updatePolicyDto.getPolicyId();

        int rowsUpdated = insurancePolicyRepository.updatePolicy(amount, endDate, premium, startDate, insuranceType, id);

        if (rowsUpdated == 0) return "provide valid details";
        return rowsUpdated + " rows updated";
    }
}