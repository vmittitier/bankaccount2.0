package com.invillia.bankaccount20;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.domain.request.DepositRequest;
import com.invillia.bankaccount20.domain.request.WithdrawRequest;
import com.invillia.bankaccount20.domain.response.AccountResponse;
import com.invillia.bankaccount20.mapper.AccountMapper;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import com.invillia.bankaccount20.services.AccountServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
class AccountServiceTests {

	@MockBean
	AccountsRepository accountsRepository;

	@MockBean
	AccountMapper accountMapper;

	@Autowired
	AccountServices accountServices;

	@Test
	void create() {
		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setBalance(200.00);
		Account account = new Account();
		account.setAccNumber(1L);
		account.setBalance(200.00);

		Mockito.when(accountMapper.accountRequestToAccount(Mockito.any())).thenReturn(account);
		Mockito.when(accountsRepository.save(Mockito.any())).thenReturn(account);
		Long accNumber = accountServices.create(accountRequest);

		Assertions.assertEquals(accNumber,account.getAccNumber());
	}

	@Test
	void withdraw() {
		Account account = new Account();
		account.setAccNumber(1L);
		account.setBalance(200.00);
		account.setAccLimit(500.00);

		WithdrawRequest withdrawRequest = new WithdrawRequest();
		withdrawRequest.setBalance(199.00);

		Mockito.when(accountsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		Mockito.when(accountsRepository.save(Mockito.any())).thenReturn(account);

		AccountResponse accountResponse = accountServices.withdraw(withdrawRequest.getBalance(),1L);

		Assertions.assertEquals(accountResponse.getBalance(),
				account.getBalance());
	}

	@Test
	void deposit() {
		Account account = new Account();
		account.setAccNumber(1L);
		account.setBalance(200.00);
		account.setAccLimit(500.00);

		DepositRequest depositRequest = new DepositRequest();
		depositRequest.setBalance(199.00);

		Mockito.when(accountsRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(account));
		Mockito.when(accountsRepository.save(Mockito.any())).thenReturn(account);
		Account accountBalanceUpt = account;
		accountServices.deposit(depositRequest.getBalance(),1L);

		Assertions.assertEquals(accountBalanceUpt.getBalance(),
				account.getBalance());
	}
}
