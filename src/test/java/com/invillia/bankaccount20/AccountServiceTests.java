package com.invillia.bankaccount20;

import com.invillia.bankaccount20.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountTests {

	Account acc;

	@Test
	void createAccount() {
		acc = new Account(200.00);
	}

}
