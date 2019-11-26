package com.invillia.bankaccount20.services;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountLimitRequest;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.domain.response.AccountResponse;
import com.invillia.bankaccount20.exception.ResourceNotFoundException;
import com.invillia.bankaccount20.exception.ValueNotAllowed;
import com.invillia.bankaccount20.mapper.AccountMapper;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServices {

    private final AccountsRepository accountsRepository;

    private final AccountMapper accountMapper;

    @Autowired
    public AccountServices(AccountsRepository accountsRepository, AccountMapper accountMapper)  {
        this.accountsRepository = accountsRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional
    public Long deposit(Double value, Long id){
        final Optional<Account> acc = accountsRepository.findById(id);

        Double currentLimit = acc.get().getAccLimit();

        Double currentValue = acc.get().getBalance();

        Double sum = currentValue + value;

        acc.get().setBalance(sum);

        Account accBalanceUpt = accountsRepository.save(acc.get());
        return accBalanceUpt.getAccNumber();
    }

    @Transactional
    public void withdraw(Double value, Long id){
        final Optional<Account> acc = accountsRepository.findById(id);

        if (value > 0) {
            if (acc.get().getBalance() + acc.get().getAccLimit() >= value) {
                if (acc.get().getBalance() >= 0) {
                    acc.get().setBalance(acc.get().getBalance() - value);
                }else {
                    acc.get().setBalance(acc.get().getBalance() + acc.get().getAccLimit() - value);
                }
            }else{
                throw new ValueNotAllowed("Value not supported.");
            }
        }else{
            throw new ValueNotAllowed("Value not allowed");
        }


        Account accountBalanceUpt = accountsRepository.save(acc.get());
        accountBalanceUpt.getAccNumber();
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
    public void update(final Long id, final AccountLimitRequest accountLimitRequest) {
        final Account account = accountsRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        accountMapper
                .updateAccountByAccountLimitRequest(account, accountLimitRequest);
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
