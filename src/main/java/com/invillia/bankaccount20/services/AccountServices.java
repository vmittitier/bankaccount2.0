package com.invillia.bankaccount20.services;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.domain.response.AccountResponse;
import com.invillia.bankaccount20.exception.ResourceNotFoundException;
import com.invillia.bankaccount20.mapper.AccountMapper;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServices {

    private final AccountsRepository accountsRepository;

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServices(AccountsRepository accountsRepository, AccountMapper accountMapper)  {
        this.accountsRepository = accountsRepository;
        this.accountMapper = accountMapper;
    }

    public double checkBalance(Account acc) {
        return acc.getBalance();
    }

    @Transactional
    public Long create(final AccountRequest accountRequest) {
        Account acc = accountMapper.accountRequestToAccount(accountRequest);
        Account account = accountsRepository.save(acc);
        return account.getAccNumber();
    }

    @Transactional
    public List<AccountResponse> findAll() {
        final List<Account> accounts = accountsRepository.findAll();
        return accountMapper.accountToAccountResponse(accounts);
    }

    @Transactional
    public AccountResponse findById(final Long id) {
        return accountsRepository.findById(id)
                .map(accountMapper::accountToAccountResponse)
                .orElseThrow(ResourceNotFoundException::new);
    }


    @Transactional
    public void update(final Long id, final AccountRequest accountRequest) {
        final Account account = accountsRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        accountMapper
                .updateAccountByAccountRequest(account, accountRequest);
        accountsRepository
                .save(account);
    }

    @Transactional
    public void delete(final Long id) {
        final Account account = accountsRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        accountsRepository.delete(account);
    }
}
