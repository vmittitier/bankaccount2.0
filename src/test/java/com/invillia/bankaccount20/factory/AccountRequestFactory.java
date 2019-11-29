package com.invillia.bankaccount20.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountRequestFactory extends JBacon<AccountRequest> {

    private final Faker faker;

    public AccountRequestFactory(final Faker faker) {
        this.faker = faker;
    }

    @Override
    protected AccountRequest getDefault() {
        final AccountRequest accountRequest = new AccountRequest();

        accountRequest.setBalance(faker.number().randomDouble(2,200,600));
        return accountRequest;

    }

    @Override
    protected AccountRequest getEmpty() {
        return new AccountRequest();
    }

    @Override
    protected void persist(final AccountRequest accountRequest) {
        throw new UnsupportedOperationException();
    }
}

