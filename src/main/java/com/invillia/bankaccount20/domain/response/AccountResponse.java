package com.invillia.bankaccount20.domain.response;

public class AccountResponse {

    private Long accNumber;

    private Double balance;

//    private String createdAt;
//
//    private String updatedAt;

    public Long getAccNumber() {
        return accNumber;
    }
//
//    public String getCreatedAt() {
//        return createdAt;
//    }

//    public void setCreatedAt(String createdAt) {
//        this.createdAt = createdAt;
//    }
//
//    public String getUpdatedAt() {
//        return updatedAt;
//    }
//
//    public void setUpdatedAt(String updatedAt) {
//        this.updatedAt = updatedAt;
//    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
