package com.invillia.bankaccount20.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountFactory  extends JBacon<Account> {

    private final AccountsRepository accountRepository;

    private final Faker faker;

    public AccountFactory(final AccountsRepository accountRepository,
                       final Faker faker) {
        this.accountRepository = accountRepository;
        this.faker = faker;
    }

    @Override
    protected Account getDefault() {
        final Account account = new Account();

        account.setBalance(faker.number().randomDouble(2,1000,2000));
        account.setAccLimit(faker.number().randomDouble(2,500,1000));

        return account;
    }

    @Override
    protected Account getEmpty() {
        return new Account();
    }

    @Override
    protected void persist(final Account account) {
        accountRepository.save(account);
    }
}