package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.DTOs.ClaimDto;
import com.Fintech.InsurancePolicy.Enums.PolicyType;
import com.Fintech.InsurancePolicy.Models.Claim;
import com.Fintech.InsurancePolicy.Models.Client;
import com.Fintech.InsurancePolicy.Models.InsurancePolicy;
import com.Fintech.InsurancePolicy.Repositories.ClaimRepository;
import com.Fintech.InsurancePolicy.Repositories.ClientRepository;
import com.Fintech.InsurancePolicy.Repositories.InsurancePolicyRepository;
import com.Fintech.InsurancePolicy.ResponseDto.ClaimResponseDto;
import com.Fintech.InsurancePolicy.UpdateDTOs.ClaimUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;
    @Autowired
    ClientRepository clientRepository;

    public String addClaim(ClaimDto claimDto) throws Exception{
        int policyId = claimDto.getPolicyId();
        int claimId = claimDto.getClientId();

        Client client = clientRepository.findById(claimId).get();
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyId).get();

        if (insurancePolicy == null) {
            return "policy not present";
        }
        else if (insurancePolicy.isPolicyClaimed()) {
            return "policy already claimed";
        }
        String claimNumber = client.getName() + "" + insurancePolicy.getPolicyNumber() + client.getContactInfo();

        Claim claim = new Claim();
        claim.setClaimDate(LocalDate.now());
        claim.setDescription("policy is Approved");
        claim.setClaimStatus("APPROVED");
        claim.setClaimNumber(claimNumber);


        claim.setClient(client);
        claim.setPolicy(insurancePolicy);

        client.getClaimList().add(claim);
        insurancePolicy.getClaims().add(claim);
        insurancePolicy.setPolicyClaimed(true);


        claim = claimRepository.save(claim);

        clientRepository.save(client);
        return "insurance claimed";
    }

    public ClaimResponseDto getClaimById(int id){

        Claim claim = claimRepository.findById(id).get();

        ClaimResponseDto claimResponseDto = ClaimResponseDto.builder().id(claim.getId())
                .claimDate(claim.getClaimDate()).claimStatus(claim.getClaimStatus())
                .claimNumber(claim.getClaimNumber()).description(claim.getDescription())
                .policyId(claim.getPolicy().getId())
                .clientId(claim.getClient().getId()).build();

        return claimResponseDto;
    }

    public List<ClaimResponseDto> getAllClaims(){
        List<Claim> claimsList = claimRepository.findAll();

        List<ClaimResponseDto> list = new ArrayList<>();
        for (Claim claim : claimsList) {
            ClaimResponseDto claimResponseDto = ClaimResponseDto.builder().id(claim.getId()).claimDate(claim.getClaimDate())
                    .claimStatus(claim.getClaimStatus()).description(claim.getDescription())
                    .claimNumber(claim.getClaimNumber()).clientId(claim.getClient().getId())
                    .policyId(claim.getPolicy().getId()).build();

            list.add(claimResponseDto);
        }
        return list;
    }

    public String deleteClaim(int id){
        Claim claim = claimRepository.findById(id).get();
        claimRepository.delete(claim);
        return "Successfully deleted a claim";
    }

    @Transactional
    public String updateClaim(ClaimUpdateDto claimUpdateDto) {
        int claimId = claimUpdateDto.getClaimId();

        LocalDate claimDate = claimUpdateDto.getClaimDate();
        String description = claimUpdateDto.getDescription();
        String claimStatus = claimUpdateDto.getClaimStatus();

        int rowsUpdated = claimRepository.updateClaim(claimId, description, claimDate, claimStatus);

        if (rowsUpdated == 0) {
            return "provide valid claim id";
        }
        return rowsUpdated + " rows updated";
    }
}