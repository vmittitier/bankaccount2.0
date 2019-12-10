package com.invillia.bankaccount20.domain.request;

public class DepositRequest {

    private Double depositValue;

    @Override
    public String toString() {
        return "DepositRequest{" +
                "depositValue=" + depositValue +
                '}';
    }

    public Double getDepositValue() {
        return depositValue;
    }

    public void setDepositValue(Double depositValue) {
        this.depositValue = depositValue;
    }
}
