package com.interest.controller;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interest.entity.Account;
import com.interest.models.AccountBalance;
import com.interest.services.AccountImpl;

@RestController
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	AccountImpl accountService;

	@PostMapping("/createAccount")
	public ResponseEntity<Account> createUser(@RequestBody Account account) {
		logger.info("Start - Account creation");
		Account accountDetails = null;
		try {
			accountDetails = accountService.createAccount(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("End - Account creation");
		return ResponseEntity.ok().body(accountDetails);

	}

	@PostMapping("/balanceUpdate")
	public ResponseEntity<Account> balanceUpdate(@RequestBody Long identification, BigDecimal balance) {
		logger.info("Start - Account Update Based on Identifier");
		Account accountDetails = null;
		try {
			accountDetails = accountService.balanceUpdate(identification, balance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("End - Account Update Based on Identifier");
		return ResponseEntity.ok().body(accountDetails);
	}

	@PostMapping("/dailyInterestAccrual")
	public ResponseEntity<Account> dailyInterestAccrual(@RequestBody Long identification) {
		logger.info("Start - Account Daily Interest Accrual");
		Account accountDetails =  null;
		try{
			accountDetails = accountService.interestAccrual(identification);
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("End - Account Daily Interest Accrual");
		return ResponseEntity.ok().body(accountDetails);
	}

	@PostMapping("/balanceUpdateToAccounts")
	public ResponseEntity<String> balanceUpdateJson(@RequestBody AccountBalance accountBalance) {
		logger.info("Start - Accounts Balance Update Json");
		accountService.accountBalanceUpdate(accountBalance);
		logger.info("End - Accounts Balance Update Json");
		return new ResponseEntity<String>("Accounts Balance completed", HttpStatus.OK);
	}

	@PostMapping("/accountClosure")
	public ResponseEntity<Account> accountClosure(@RequestBody Long identification) {
		logger.info("Start - Accounts Closure");
		Account accountDetails = null;
		try{
			accountDetails = accountService.accountClosure(identification);
		}catch (Exception e) {
			e.printStackTrace();
		} 
		logger.info("End - Accounts Closure");
		return ResponseEntity.ok().body(accountDetails);
	}

	@PostMapping("/monthlyInterestUpdate")
	public ResponseEntity<Account> monthlyInterestUpdate(@RequestBody Long identification) {
		logger.info("Start - Account Monthly Interest Update");
		Account accountDetails = null;
		try{
			accountDetails = accountService.accountMonthlyInterest(identification);
		}catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("End - Account Monthly Interest Update");
		return ResponseEntity.ok().body(accountDetails);
	}
}
