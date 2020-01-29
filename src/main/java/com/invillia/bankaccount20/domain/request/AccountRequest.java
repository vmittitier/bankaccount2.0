package com.invillia.bankaccount20.domain.request;

import javax.validation.constraints.NotNull;

public class AccountRequest {

    @NotNull(message = "Balance value cant be zero!")
    private Double balance;

    public AccountRequest(@NotNull Double balance) {
        this.balance = balance;
    }

    public AccountRequest() {
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
