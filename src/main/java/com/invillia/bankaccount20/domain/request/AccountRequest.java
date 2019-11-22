package com.invillia.bankaccount20.domain.request;

import javax.validation.constraints.NotNull;

public class AccountRequest {

    @NotNull(message = "Balance value cant be zero!")
    private Double balance;


    @NotNull(message = "Account number must be a valid number!")
    private Long accNumber;

    public AccountRequest(@NotNull Double balance) {
        this.balance = balance;
    }

    public AccountRequest(@NotNull Double balance, @NotNull Long accNumber) {
        this.balance = balance;
        this.accNumber = accNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public AccountRequest() {
    }
}
