package com.invillia.bankaccount20.domain.request;

import javax.validation.constraints.NotNull;

public class AccountLimitRequest {

//    @NotNull(message = "Balance value cant be zero!")
//    private Double balance;
    private Double accLimit;

    public AccountLimitRequest() {    }

    public AccountLimitRequest(@NotNull Double accLimit) {
        this.accLimit = accLimit;
    }

    public Double getBalance() {
        return accLimit;
    }

    public void setBalance(Double accLimit) {
        this.accLimit = accLimit;
    }

}
