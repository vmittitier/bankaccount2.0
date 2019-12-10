package com.invillia.bankaccount20.controller;

import com.invillia.bankaccount20.domain.request.AccountLimitRequest;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.domain.request.DepositRequest;
import com.invillia.bankaccount20.domain.request.WithdrawRequest;
import com.invillia.bankaccount20.domain.response.AccountResponse;
import com.invillia.bankaccount20.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountServices accountServices;

    @Autowired
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
    public AccountResponse withdraw(@PathVariable final Long id,
                                    @RequestBody WithdrawRequest withdrawRequest){
        accountServices.withdraw(withdrawRequest.getBalance(),id);
        return accountServices.findById(id);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteById(@PathVariable final Long id){
        accountServices.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
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
                                @Valid @RequestBody final AccountLimitRequest accountLimitRequest) {

        accountServices.update(id, accountLimitRequest);
        return ResponseEntity.noContent().build();
    }

}
