package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.DTOs.InsurancePolicyRequestDto;
import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import com.Fintech.InsurancePolicy.Repositories.InsurancePolicyRepository;
import com.Fintech.InsurancePolicy.ResponseDto.PolicyResponseDto;
import com.Fintech.InsurancePolicy.UpdateDTOs.UpdatePolicyDto;
import net.bytebuddy.asm.Advice;
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

    public PolicyResponseDto getPolicyById(int id){
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();

        // id | coverage_amount | end_date   | is_policy_claimed
        // | policy_number | premium | start_date | type           | client_id |

        PolicyResponseDto policyResponseDto = PolicyResponseDto.builder().id(insurancePolicy.getId())
                .coverage_amount(insurancePolicy.getCoverage_amount())
                .endDate(insurancePolicy.getEndDate()).isPolicyClaimed(insurancePolicy.isPolicyClaimed())
                .policyNumber(insurancePolicy.getPolicyNumber()).premium(insurancePolicy.getPremium())
                .startDate(insurancePolicy.getStartDate()).policyType(insurancePolicy.getType()+"")
                .clientId(insurancePolicy.getClient().getId()).build();

        return policyResponseDto;
    }

    public List<PolicyResponseDto> getAllPolicies(){

        List<InsurancePolicy> insurancePolicyList = insurancePolicyRepository.findAll();
        List<PolicyResponseDto> policyResponseList = new ArrayList<>();

        for (InsurancePolicy insurancePolicy : insurancePolicyList) {
            PolicyResponseDto policyResponseDto = PolicyResponseDto.builder().id(insurancePolicy.getId())
                    .coverage_amount(insurancePolicy.getCoverage_amount())
                    .endDate(insurancePolicy.getEndDate()).isPolicyClaimed(insurancePolicy.isPolicyClaimed())
                    .policyNumber(insurancePolicy.getPolicyNumber()).premium(insurancePolicy.getPremium())
                    .startDate(insurancePolicy.getStartDate()).policyType(insurancePolicy.getType()+"")
                    .clientId(insurancePolicy.getClient().getId()).build();

            policyResponseList.add(policyResponseDto);
        }
        return policyResponseList;
    }

    public String deletePolicy(int id) {
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(id).get();
        insurancePolicyRepository.delete(insurancePolicy);
        return "policy deleted successfully";
    }

    @Transactional
    public String updatePolicy(UpdatePolicyDto updatePolicyDto) {
//        UPDATE policies SET coverage_amount=:amount, end_date:endDate,
//                premium=:premium, start_date=:startDate, type=:insuranceType WHERE id=:policyId;
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