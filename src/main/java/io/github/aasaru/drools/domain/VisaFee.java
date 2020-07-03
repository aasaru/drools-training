package io.github.aasaru.drools.domain;

public class VisaFee {

    private int applicationId;
    private Double feeAmount;

    public VisaFee(int applicationId, Integer feeAmount) {
        this.applicationId = applicationId;
        this.feeAmount = feeAmount.doubleValue();
    }

    public VisaFee(int applicationId, Double feeAmount) {
        this.applicationId = applicationId;
        this.feeAmount = feeAmount;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Double getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Double feeAmount) {
        this.feeAmount = feeAmount;
    }

    @Override
    public String toString() {
        return "VisaFee(applicationId:" + applicationId + ", feeAmount:" + feeAmount + ")";
    }
}
