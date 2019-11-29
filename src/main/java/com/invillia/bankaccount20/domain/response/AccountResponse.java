package com.invillia.bankaccount20.domain.response;

public class AccountResponse {

    private Long accNumber;

    private Double balance;

    private Double accLimit;

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setAccLimit(Double accLimit) {
        this.accLimit = accLimit;
    }

    public Double getBalance() {
        return balance;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public Double getAccLimit() {
        return accLimit;
    }
}
