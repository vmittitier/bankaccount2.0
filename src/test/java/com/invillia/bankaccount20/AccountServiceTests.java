package com.invillia.bankaccount20;

import com.invillia.bankaccount20.domain.Account;
import com.invillia.bankaccount20.domain.request.AccountRequest;
import com.invillia.bankaccount20.mapper.AccountMapper;
import com.invillia.bankaccount20.repositories.AccountsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class AccountServiceTests {

	@MockBean
	AccountsRepository accountsRepository;

	@MockBean
	AccountMapper accountMapper;

	@Test
	void create() {

		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setBalance(200.00);
		Account account = new Account();
		account.setSaldo(200.00);
		Mockito.when(accountsRepository.save(Mockito.any())).thenReturn(account);
		Mockito.when(accountMapper.accountRequestToAccount(Mockito.any())).thenReturn(account);
		accountsRepository.save(account);
		Assertions.assertEquals(accountRequest.getAccNumber(), account.getAccNumber());
	}


}
