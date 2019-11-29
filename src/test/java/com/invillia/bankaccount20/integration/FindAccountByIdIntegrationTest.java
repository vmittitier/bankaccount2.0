package com.invillia.bankaccount20.integration;

import com.invillia.bankaccount20.Response;
import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.factory.AccountFactory;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.format.DateTimeFormatter;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindAccountByIdIntegrationTest {


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");


    private final AccountFactory accountFactory;

    @Autowired
    FindAccountByIdIntegrationTest(final AccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    @Test
    void findByIdWithSuccess() {
        final Account account = accountFactory.create();

        RestAssured
                .given()
                .log().all()
                .when()
                .get("/account/1")
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("accNumber", Matchers.is(account.getAccNumber().intValue()))
                .body("balance", Matchers.equalTo(Float.parseFloat(account.getBalance().toString())))
                .body("accLimit", Matchers.equalTo(Float.parseFloat(account.getAccLimit().toString())));
    }

    @Test
    void findByIdNotFound() {
        RestAssured
                .given()
                .log().all()
                .when()
                .get("/account/1")
                .then()
                .log().all()
                .specification(Response.notFound());
    }


}