package com.invillia.bankaccount20.integration;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.exception.ResourceNotFoundException;
import com.invillia.bankaccount20.factory.AccountRequestFactory;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreateAccountIntegrationTest {

    private final AccountsRepository accountRepository;

    private final AccountRequestFactory accountRequestFactory;

    @Autowired
    CreateAccountIntegrationTest(final AccountsRepository accountRepository,
                              final AccountRequestFactory accountRequestFactory) {
        this.accountRepository = accountRepository;
        this.accountRequestFactory = accountRequestFactory;
    }

    @Test
    void createAccountWithSuccessTest() {
        final AccountRequest accountRequest = accountRequestFactory.build();

        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                .body(accountRequest)
                    .when()
                    .post("/account")
                .then()
                    .log().all()
                    .statusCode(SC_CREATED)
                    .header("Location", Matchers.endsWith("/account/1"));

        Assertions.assertEquals(1, accountRepository.count());

        final Account account = accountRepository.findById(1L)
                .orElseThrow(ResourceNotFoundException::new);

        Assertions.assertAll("account assert",
                () -> Assertions.assertEquals(accountRequest.getBalance(), account.getBalance()));
    }

    @Test
    void createAccountWithoutSuccessTest() {
        RestAssured
                .given()
                    .log().all()
                    .contentType(ContentType.JSON)
                .body(new AccountRequest())
                    .when()
                    .post("/account")
                .then()
                    .log().all()
                    .statusCode(SC_BAD_REQUEST);
    }
}
