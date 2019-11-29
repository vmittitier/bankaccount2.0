package com.invillia.bankaccount20.integration;

import com.invillia.bankaccount20.Response;
import com.invillia.bankaccount20.factory.AccountFactory;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteAccountIntegrationTest  {

    private final AccountFactory accountFactory;

    private final AccountsRepository accountRepository;

    @Autowired
    public DeleteAccountIntegrationTest(final AccountFactory accountFactory,
                                        final AccountsRepository accountRepository) {
        this.accountFactory = accountFactory;
        this.accountRepository = accountRepository;
    }

    @Test
    void deleteWithSuccess(){
        accountFactory.create();

        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/account/1")
                .then()
                    .log().all()
                    .statusCode(SC_NO_CONTENT);

        Assertions.assertEquals(0, accountRepository.count());
    }

    @Test
    void deleteByIdNotFoundTest() {
        RestAssured
                .given()
                    .log().all()
                .when()
                    .delete("/account/1")
                .then()
                  .log().all()
                  .specification(Response.notFound());
    }

}
