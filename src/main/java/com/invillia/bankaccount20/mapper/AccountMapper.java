package com.invillia.bankaccount20.mapper;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountLimitRequest;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.domain.response.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    public Account accountRequestToAccount(final AccountRequest accountRequest) {
        final Account account = new Account();
        account.setBalance(accountRequest.getBalance());
        return account;
    }

    public AccountResponse accountToAccountResponse(final Account account) {
        final AccountResponse accountResponse = new AccountResponse();
        accountResponse.setAccNumber(account.getAccNumber());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setAccLimit(account.getAccLimit());
        return accountResponse;
    }

    public List<AccountResponse> accountToAccountResponse(final List<Account> account) {
        return account.stream()
                .map(this::accountToAccountResponse)
                .collect(Collectors.toList());
    }

    public void updateAccountByAccountRequest(final Account account, final AccountRequest accountRequest) {
        account.setBalance(accountRequest.getBalance());
    }

    public void updateAccountByAccountLimitRequest(Account account, AccountLimitRequest accountLimitRequest) {
        account.setAccLimit(accountLimitRequest.getAccLimit());

    }
}
