package com.Fintech.InsurancePolicy.DTOs;

import com.Fintech.InsurancePolicy.Enums.PolicyType;

public class ClaimDto {

    private int policyId;
    private int clientId;
//    private String policyType;

    public ClaimDto() {
    }

    public ClaimDto(int policyId, int clientId, String policyType) {
        this.policyId = policyId;
        this.clientId = clientId;
//        this.policyType = policyType;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }
//
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
//
//    public void setPolicyType(String policyType) {
//        this.policyType = policyType;
//    }
//
//    public String getPolicyType() {
//        return this.policyType;
//    }
}
