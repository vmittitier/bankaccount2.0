package com.invillia.bankaccount20.domain.request;

public class WithdrawRequest {

    private Double balance;

    @Override
    public String toString() {
        return "WithdrawRequest{" +
                "balance=" + balance +
                '}';
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }
}
