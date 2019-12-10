package com.invillia.bankaccount20.domain.request;

import javax.validation.constraints.NotNull;

public class AccountLimitRequest {

    @NotNull(message = "Balance value cant be zero!")
    private Double accLimit;

    public AccountLimitRequest() {    }

    public AccountLimitRequest(@NotNull Double accLimit) {
        this.accLimit = accLimit;
    }

    public Double getAccLimit() {
        return accLimit;
    }

    public void setAccLimit(Double accLimit) {
        this.accLimit = accLimit;
    }

}
