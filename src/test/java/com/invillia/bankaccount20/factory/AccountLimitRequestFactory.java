package com.invillia.bankaccount20.factory;

import br.com.leonardoferreira.jbacon.JBacon;
import com.github.javafaker.Faker;
import com.invillia.bankaccount20.domain.request.AccountLimitRequest;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountLimitRequestFactory extends JBacon<AccountLimitRequest> {

    private final Faker faker;

    public AccountLimitRequestFactory(final Faker faker) {
        this.faker = faker;
    }

    @Override
    protected AccountLimitRequest getDefault() {
        final AccountLimitRequest accountLimitRequest = new AccountLimitRequest();

        accountLimitRequest.setBalance(faker.number().randomDouble(2,200,600));
        return accountLimitRequest;
    }


    @Override
    protected AccountLimitRequest getEmpty() {
        return new AccountLimitRequest();
    }

    @Override
    protected void persist(final AccountLimitRequest accountLimitRequest){
            throw new UnsupportedOperationException();
    }
}
