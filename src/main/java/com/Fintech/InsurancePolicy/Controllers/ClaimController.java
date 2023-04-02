package com.Fintech.InsurancePolicy.Controllers;
import com.Fintech.InsurancePolicy.DTOs.ClaimDto;
import com.Fintech.InsurancePolicy.Models.Claim;
import com.Fintech.InsurancePolicy.ResponseDto.ClaimResponseDto;
import com.Fintech.InsurancePolicy.Services.ClaimService;
import com.Fintech.InsurancePolicy.UpdateDTOs.ClaimUpdateDto;
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
    public ResponseEntity<String> claimPolicy(@RequestBody ClaimDto claimDto){
        try {
            String response = claimService.addClaim(claimDto);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            String result = e.getLocalizedMessage();
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/claims/{id}")
    public ResponseEntity<ClaimResponseDto> getClaimById(@PathVariable int id){
        try {
            ClaimResponseDto claimResponseDto = claimService.getClaimById(id);
            return new ResponseEntity<>(claimResponseDto, HttpStatus.FOUND);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_all_claims")
    public ResponseEntity<List<ClaimResponseDto>> getAllClaims(){
        List<ClaimResponseDto> list =  claimService.getAllClaims();
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/claims/{id}")
    public ResponseEntity<String> deleteClaim(@PathVariable int id){
        String response = "";
        try{
            response =  claimService.deleteClaim(id);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }catch (Exception e){
            response = "Requested Claim is not present!!";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("updateClaim")
    public ResponseEntity<String> updateClaim(@RequestBody ClaimUpdateDto claimUpdateDto) {
        try {
            return new ResponseEntity<>(claimService.updateClaim(claimUpdateDto), HttpStatus.ACCEPTED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


