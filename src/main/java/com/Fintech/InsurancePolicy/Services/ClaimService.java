package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.DTOs.ClaimDto;
import com.Fintech.InsurancePolicy.Models.Claim;
import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.Repositories.ClaimRepository;
import com.Fintech.InsurancePolicy.Repositories.InsurancePolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;

    public String addClaim(ClaimDto claimDto){

        Claim claim = new Claim();
        claim.setClaimDate(claimDto.getClaimDate());
        claim.setClaimNumber(claimDto.getClaimNumber());
        claim.setClaimStatus(claimDto.getClaimStatus());
        claim.setDescription(claimDto.getDescription());


        int policyId = claimDto.getPolicyId();
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyId).get();

        claim.setPolicy(insurancePolicy);
        claimRepository.save(claim);
        return "Claim added successfully";
    }

    public Claim getClaimById(int id){
        Claim claim = claimRepository.findById(id).get();
        return claim;
    }

    public List<Claim> getAllClaims(){
        List<Claim> claimsList = claimRepository.findAll();
        return claimsList;
    }

    public String deleteClaim(int id){
        Claim claim = claimRepository.findById(id).get();
        claimRepository.delete(claim);
        return "Successfully deleted a claim";
    }
}