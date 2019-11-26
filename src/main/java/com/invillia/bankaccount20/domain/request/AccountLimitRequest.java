package com.invillia.bankaccount20.domain.request;

import javax.validation.constraints.NotNull;

public class AccountLimitRequest {

    @NotNull(message = "Balance value cant be zero!")
    private Double balance;

    public AccountLimitRequest() {    }

    public AccountLimitRequest(@NotNull Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
