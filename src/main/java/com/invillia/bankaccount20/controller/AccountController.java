package com.invillia.bankaccount20.controller;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.domain.request.DepositRequest;
import com.invillia.bankaccount20.domain.request.WithdrawRequest;
import com.invillia.bankaccount20.domain.response.AccountResponse;
import com.invillia.bankaccount20.services.AccountServices;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountServices accountServices;

    public AccountController(AccountServices accountServices) {
        this.accountServices = accountServices;
    }

    @GetMapping
    public List<AccountResponse> findAll(){
        return accountServices.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable final Long id) {
        return accountServices.findById(id);
    }

    @PutMapping("/deposit/{id}")
    public HttpEntity<?> deposit(@PathVariable final Long id,
                                 @RequestBody DepositRequest depositRequest){
        accountServices.deposit(depositRequest.getBalance(),id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/withdraw/{id}")
    public HttpEntity<?> withdraw(@PathVariable final Long id,
                                  @RequestBody WithdrawRequest withdrawRequest){
        accountServices.withdraw(withdrawRequest.getBalance(),id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    public HttpEntity<?> create(@RequestBody @Valid final AccountRequest accountRequest) {
        final Long id = accountServices.create(accountRequest);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/account/{id}")
                .build(id);
        return ResponseEntity
                .created(location)
                .build();
    }

    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable final Long id,
                                @Valid @RequestBody final AccountRequest accountRequest) {
        accountServices.update(id, accountRequest);
        return ResponseEntity.noContent().build();
    }

}
