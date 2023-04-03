package com.Fintech.InsurancePolicy.Services;

import com.Fintech.InsurancePolicy.Converters.ClaimEntityToResponseDto;
import com.Fintech.InsurancePolicy.RequestDTOs.InsuranceClaimDto;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimService {

    @Autowired
    ClaimRepository claimRepository;
    @Autowired
    InsurancePolicyRepository insurancePolicyRepository;
    @Autowired
    ClientRepository clientRepository;

    public String claimInsurance(InsuranceClaimDto insuranceClaimDto) throws Exception {

        int policyId = insuranceClaimDto.getPolicyId();
        int claimId = insuranceClaimDto.getClientId();
        double amountToClaim = insuranceClaimDto.getAmountToClaim();
        String description = insuranceClaimDto.getDescription();

        // getting the parent Entities of Claim Entity -> Client & InsurancePolicy.
        Client client = clientRepository.findById(claimId).get();
        InsurancePolicy insurancePolicy = insurancePolicyRepository.findById(policyId).get();

        if (insurancePolicy == null) {
            return "policy not present";
        }
        if (insurancePolicy.getCoverage_amount() < amountToClaim) {
            return "The requested amount can't be claimed." + "Your coverage amount is " + insurancePolicy.getCoverage_amount();
        }

        String claimNumber = client.getName() + "" + insurancePolicy.getPolicyNumber();
        double coverageAmount = insurancePolicy.getCoverage_amount() - amountToClaim;

        // created a claim object and setting its attributes below.
        Claim claim = new Claim();
        claim.setClaimDate(LocalDate.now());
        claim.setDescription(description);
        claim.setClaimStatus("APPROVED");
        claim.setClaimNumber(claimNumber);

        // setting the foreign key attributes
        claim.setClient(client);
        claim.setPolicy(insurancePolicy);

        // adding the claim object to the Client and InsurancePolicy as it is a child to Client and InsurancePolicy Entities.
        client.getClaimList().add(claim);
        insurancePolicy.getInsuranceClaims().add(claim);
        insurancePolicy.setCoverage_amount(coverageAmount);

        claim = claimRepository.save(claim);

        // By saving the parent Client, the child entities will also get saved.
        clientRepository.save(client);
        return "insurance claimed";
    }

    public ClaimResponseDto getClaimById(int id) throws Exception{

        Claim claim = claimRepository.findById(id).get();

        // A Helper function to convert Claim Entity to ClaimResponseDto.
        ClaimResponseDto claimResponseDto = ClaimEntityToResponseDto.entityToResponseDto(claim);

        return claimResponseDto;
    }

    public List<ClaimResponseDto> getAllClaims() throws Exception {
        List<Claim> claimsList = claimRepository.findAll();

        List<ClaimResponseDto> list = new ArrayList<>();
        for (Claim claim : claimsList) {
            // A Helper function to convert Claim Entity to ClaimResponseDto.
            ClaimResponseDto claimResponseDto = ClaimEntityToResponseDto.entityToResponseDto(claim);

            list.add(claimResponseDto);
        }
        return list;
    }

    public String deleteClaim(int id) throws Exception {

        Claim claim = claimRepository.findById(id).get();
        claimRepository.delete(claim);

        return "Successfully deleted a claim";
    }

    @Transactional
    public String updateClaim(ClaimUpdateDto claimUpdateDto) throws Exception {

        int claimId = claimUpdateDto.getClaimId();

        LocalDate claimDate = claimUpdateDto.getClaimDate();
        String description = claimUpdateDto.getDescription();
        String claimStatus = claimUpdateDto.getClaimStatus();

        // calling updateClaim method in claimRepository to update Claim Details.
        int rowsUpdated = claimRepository.updateClaim(claimId, description, claimDate, claimStatus);

        if (rowsUpdated == 0) {
            return "provide valid claim id";
        }

        return rowsUpdated + " rows updated";
    }
}