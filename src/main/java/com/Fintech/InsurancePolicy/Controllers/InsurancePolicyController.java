package com.Fintech.InsurancePolicy.Controllers;

import com.Fintech.InsurancePolicy.DTOs.InsurancePolicyRequestDto;
import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.ResponseDto.PolicyResponseDto;
import com.Fintech.InsurancePolicy.Services.InsurancePolicyService;
import com.Fintech.InsurancePolicy.UpdateDTOs.UpdatePolicyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InsurancePolicyController {

    @Autowired
    InsurancePolicyService insurancePolicyService;

    @PostMapping("/policies")
    public ResponseEntity<String> addInsurancePolicy(@RequestBody InsurancePolicyRequestDto insurancePolicyRequestDto) {
        String response = "";
        try {
            response = insurancePolicyService.addInsurancePolicy(insurancePolicyRequestDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response = "policy not added";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    //API to fetch policy by id.
    @GetMapping("/policies/{id}")
    public ResponseEntity<PolicyResponseDto> getPolicyById(@PathVariable int id) {
        PolicyResponseDto policyResponseDto = insurancePolicyService.getPolicyById(id);
        return new ResponseEntity<>(policyResponseDto, HttpStatus.ACCEPTED);
    }


    //Api to fetch all policies
    @GetMapping("/get_all_policies")
    public ResponseEntity<List<PolicyResponseDto>> getAllPolicies() {
        List<PolicyResponseDto> policyResponseDtos = insurancePolicyService.getAllPolicies();
        return new ResponseEntity<>(policyResponseDtos, HttpStatus.ACCEPTED);
    }

    //API to delete a policy by id
    @DeleteMapping("/policies/{id}")
    public ResponseEntity<String> deletePolicy(@PathVariable int id) {
        try {
            String response = insurancePolicyService.deletePolicy(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            String response = "policy not deleted";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updatePolicy")
    public ResponseEntity<String> updatePolicy(@RequestBody UpdatePolicyDto updatePolicyDto) {
        try {
            String response = insurancePolicyService.updatePolicy(updatePolicyDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
