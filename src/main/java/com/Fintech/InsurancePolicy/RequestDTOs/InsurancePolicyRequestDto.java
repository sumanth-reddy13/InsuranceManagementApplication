package com.Fintech.InsurancePolicy.RequestDTOs;

import com.Fintech.InsurancePolicy.Enums.PolicyType;

import java.time.LocalDate;

public class InsurancePolicyRequestDto {

    private String policyNumber;
    private PolicyType type;
    private double coverage_amount;
    private double premium;
    private LocalDate startDate;
    private LocalDate endDate;
    private int clientId;

    public InsurancePolicyRequestDto() {

    }
    public String getPolicyNumber() {
        return policyNumber;
    }
    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    public PolicyType getType() {
        return type;
    }
    public void setType(PolicyType type) {
        this.type = type;
    }
    public double getCoverage_amount() {
        return coverage_amount;
    }
    public void setCoverage_amount(double coverage_amount) {
        this.coverage_amount = coverage_amount;
    }
    public double getPremium() {
        return premium;
    }
    public void setPremium(double premium) {
        this.premium = premium;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public int getClientId() {
        return clientId;
    }
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
