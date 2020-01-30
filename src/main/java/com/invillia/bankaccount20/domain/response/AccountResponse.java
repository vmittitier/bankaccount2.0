package com.invillia.bankaccount20.domain.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountResponse {

    @JsonIgnore
    private Long accNumber;

    private Double balance;

    private Double accLimit;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public Double getAccLimit() {
        return accLimit;
    }

    public void setAccLimit(Double accLimit) {
        this.accLimit = accLimit;
    }
}
