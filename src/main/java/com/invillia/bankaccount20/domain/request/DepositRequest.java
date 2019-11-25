package com.invillia.bankaccount20.domain.request;

public class DepositRequest {

    private Double balance;

    @Override
    public String toString() {
        return "DepositRequest{" +
                "balance=" + balance +
                '}';
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
