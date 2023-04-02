package com.Fintech.InsurancePolicy.Controllers;
import com.Fintech.InsurancePolicy.DTOs.ClaimDto;
import com.Fintech.InsurancePolicy.Models.Claim;
import com.Fintech.InsurancePolicy.Services.ClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/claimInsurance")
public class ClaimController {

    @Autowired
    ClaimService claimService;

    @PostMapping("/claims")
    public ResponseEntity<String> addClaim(@RequestBody ClaimDto claimDto){
        try {
            String response = claimService.addClaim(claimDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            String result = "Insurance not claimed.";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/claims/{id}")
    public Claim getClaimById(@PathVariable int id){

        return claimService.getClaimById(id);
    }

    @GetMapping("/get_all_claims")
    public List<Claim> getAllClaims(){
        return claimService.getAllClaims();
    }

    @DeleteMapping("/claims/{id}")
    public String deleteClaim(@PathVariable int id){
        try{
            return claimService.deleteClaim(id);
        }catch (Exception e){
            String response = "Unfortunately, Your Claim is not deleted!";
            return response;
        }
    }
}


