package com.invillia.bankaccount20.integration;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountLimitRequest;
import com.invillia.bankaccount20.exception.ResourceNotFoundException;
import com.invillia.bankaccount20.factory.AccountFactory;
import com.invillia.bankaccount20.factory.AccountLimitRequestFactory;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateAccountIntegrationTest {

    private final AccountFactory accountFactory;

    private final AccountLimitRequestFactory accountLimitRequestFactory;

    private final AccountsRepository accountsRepository;

    @Autowired
    public UpdateAccountIntegrationTest(final AccountFactory accountFactory,
                                        final AccountLimitRequestFactory accountLimitRequestFactory,
                                        final AccountsRepository accountsRepository) {
        this.accountFactory = accountFactory;
        this.accountLimitRequestFactory = accountLimitRequestFactory;
        this.accountsRepository = accountsRepository;
    }

    @Test
    void updateWithSuccessTest(){

        accountFactory.create();

        final AccountLimitRequest accountLimitRequest = accountLimitRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(accountLimitRequest)
                .when()
                    .put("/account/1")
                .then()
                    .log().all()
                    .statusCode(SC_NO_CONTENT);

        final Account account = accountsRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("account assert",
                () -> Assertions.assertEquals(accountLimitRequest.getAccLimit(), account.getAccLimit()));
    }
}
