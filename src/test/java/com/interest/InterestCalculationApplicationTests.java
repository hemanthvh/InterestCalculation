package com.interest;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.interest.entity.Account;
import com.interest.mapper.AccountMapper;
import com.interest.services.AccountImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InterestCalculationApplicationTests {

	@Autowired
	private AccountImpl accountService;
	
	@Autowired
	private AccountMapper accountMapper;
	
	@Test
	public void createAccount()
	{
		Account account = new Account();
		account.setBranchCode("182182");
		account.setIdentification(111222333);
		account.setOpeningDate(new Date());
		account.setFirstName("Hemanth");
		account.setBalance(BigDecimal.valueOf(10));
		accountService.createAccount(account);
	}
	
	@Test
	public void updateAccount() {
		
		Account account = accountMapper.findAccount(Long.parseLong("111222333"));
		account.setBalance(BigDecimal.valueOf(100));
		Account accountDetails = accountService.balanceUpdate(account);
		assertNotNull(accountDetails);
		
	}
	
	@Test
	public void dailyAccrual() {
		
		Account account = accountMapper.findAccount(Long.parseLong("111222333"));
		Account accountDetails = accountService.interestAccrual(account.getIdentification());
		assertNotNull(accountDetails);
		
	}
	
	
}
