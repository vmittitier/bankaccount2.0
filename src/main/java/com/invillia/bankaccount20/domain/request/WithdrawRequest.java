package com.invillia.bankaccount20.domain.request;

public class WithdrawRequest {

    private Double withdrawValue;

    @Override
    public String toString() {
        return "WithdrawRequest{" +
                "withdrawValue=" + withdrawValue +
                '}';
    }

    public Double getWithdrawValue() {
        return withdrawValue;
    }

    public void setWithdrawValue(Double withdrawValue) {
        this.withdrawValue = withdrawValue;
    }
}
