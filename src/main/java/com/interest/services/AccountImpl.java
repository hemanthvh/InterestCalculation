package com.interest.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.interest.entity.Account;
import com.interest.mapper.AccountMapper;
import com.interest.models.AccountBalance;
import com.interest.models.AccountInfo;
import com.interest.models.BalanceInfo;
import com.interest.util.InterestCalculationUtil;
import com.interest.util.Utility;

@Service
public class AccountImpl {

	private static final Logger logger = LoggerFactory.getLogger(AccountImpl.class);
	
	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private InterestCalculationUtil intUtl;

	@Autowired
	private Utility util;

	@Transactional
	public Account createAccount(Account account) {
		logger.debug("Start" + account.toString());
		// Account creation
		if (account.getOpeningDate() == null) {
			account.setOpeningDate(new Date());
		}
		accountMapper.save(account);

		util.saveHist(account, "", "Created Account");

		Account accountDetails = accountMapper.findAccount(account.getIdentification());
		
		publisher.publishEvent(account);
		logger.debug("End" + account.toString());
		return accountDetails;
	}

	public void accountBalanceUpdate(AccountBalance accountBalance) {
		logger.debug("Account Balance Update" + accountBalance);
		BalanceInfo balInfo = accountBalance.getBalanceInfo();
		String balanceDate = accountBalance.getBalanceInfo().getBalanceDate();

		for (AccountInfo actInfo : balInfo.getAccountInfo()) {
			Account accountDetails = accountMapper.findAccount(actInfo.getIdentification());
			if (accountDetails != null) {
				balanceUpdate(accountDetails.getIdentification(), BigDecimal.valueOf(actInfo.getBalance()), balanceDate);
			}
			else {
				logger.info("Account Information Does not Exists " + actInfo.getIdentification());
			}
			
		}

	}

	public Account balanceUpdate(Account account) {

		logger.debug("Account Balance Update" + account);
		
		String oldValue = null;
		String newValue = null;
		String tso = null;
		Account accountDetails = accountMapper.findAccount(account.getIdentification());

		oldValue = String.valueOf(accountDetails.getBalance());

		if (accountDetails != null) {
			accountDetails.setBalance(account.getBalance());
		}
		accountMapper.save(accountDetails);

		newValue = String.valueOf(accountDetails.getBalance());
		tso = "OldValue " + oldValue + ":" + "NewValue " + newValue;
		util.saveHist(account, tso, "Balance Update");

		publisher.publishEvent(account);
		
		logger.debug("Account Balance Updated");

		return accountDetails;
	}

	public Account balanceUpdate(Long identification, BigDecimal balance) {

		logger.debug("Account Balance Update" + identification);
		String oldValue = null;
		String newValue = null;
		String tso = null;
		Account accountDetails = accountMapper.findAccount(identification);

		oldValue = String.valueOf(accountDetails.getBalance());

		if (accountDetails != null) {
			accountDetails.setBalance(balance);
		}
		accountMapper.save(accountDetails);

		newValue = String.valueOf(accountDetails.getBalance());
		tso = "OldValue " + oldValue + ":" + "NewValue " + newValue;
		util.saveHist(accountDetails, tso, "Balance Update");

		publisher.publishEvent(accountDetails);

		logger.debug("Account Balance Update");
		
		return accountDetails;
	}

	public Account balanceUpdate(Long identification, BigDecimal balance, String balanceDate) {
		logger.debug("Account Balance Update" + identification);
		String oldValue = null;
		String newValue = null;
		String tso = null;
		Account accountDetails = accountMapper.findAccount(identification);

		oldValue = String.valueOf(accountDetails.getBalance());

		if (accountDetails != null) {
			accountDetails.setBalance(balance);
		}
		accountMapper.save(accountDetails);

		newValue = String.valueOf(accountDetails.getBalance());
		tso = "BalancingDate" + balanceDate + "OldValue " + oldValue + ":" + "NewValue " + newValue;
		util.saveHist(accountDetails, tso, "Balance Update");

		publisher.publishEvent(accountDetails);
		logger.debug("Account Balance Update");
		return accountDetails;
	}

	public Account interestAccrual(Long identification) {
		logger.debug("Interest Accrual" + identification);
		String oldValue = null;
		String newValue = null;
		String tso = null;
		Account accountDetails = accountMapper.findAccount(identification);
		oldValue = String.valueOf(accountDetails.getInterestAccrual());
		
		BigDecimal dailyInt = intUtl.dailyInterestArrucal(accountDetails.getBalance(),
				accountDetails.getInterestRate());

		if (accountDetails != null) {
			accountDetails.setInterestAccrual(accountDetails.getInterestAccrual().add(dailyInt));
		}
		accountMapper.save(accountDetails);
		newValue = String.valueOf(accountDetails.getInterestAccrual());
		tso = "OldValue " + oldValue + ":" + "NewValue " + newValue;
		util.saveHist(accountDetails, tso, "Daily Interest Update");

		publisher.publishEvent(accountDetails);
		logger.debug("Interest Accrual" + identification);
		return accountDetails;
	}
	
	public Account accountClosure(Long identification) {
		logger.debug("AccountClosure" + identification);
		String oldValue = null;
		String newValue = null;
		String tso = null;
		Account accountDetails = accountMapper.findAccount(identification);
		oldValue = String.valueOf(accountDetails.getClosingDate());
		BigDecimal dailyInt = intUtl.dailyInterestArrucal(accountDetails.getBalance(),
				accountDetails.getInterestRate());

		if ((accountDetails != null) && (accountDetails.getClosingDate() == null)) {
			
			BigDecimal accrual = accountDetails.getInterestAccrual().add(dailyInt);
			accountDetails.setInterestAccrual(new BigDecimal(0));
			accountDetails.setClosingDate(new Date());
			accountDetails.setBalance(accountDetails.getBalance().add(accrual));
		}
		accountMapper.save(accountDetails);
		newValue = String.valueOf(accountDetails.getClosingDate());
		tso = "ClosureDate " +" OldValue " + oldValue + ":" + "NewValue " + newValue;
		tso = tso + "Interst Amount" + accountDetails.getInterestAccrual();
		util.saveHist(accountDetails, tso, "Account Closed");

		publisher.publishEvent(accountDetails);
		logger.debug("AccountClosure");
		return accountDetails;
	}
	
	public Account accountMonthlyInterest(Long identification) {
		logger.debug("Monthly Interest" + identification);
		String tso = null;
		BigDecimal accrual = null;
		Account accountDetails = accountMapper.findAccount(identification);
		BigDecimal dailyInt = intUtl.dailyInterestArrucal(accountDetails.getBalance(),
				accountDetails.getInterestRate());

		if ((accountDetails != null) && (accountDetails.getClosingDate() == null)) {
			
			accrual = accountDetails.getInterestAccrual().add(dailyInt);
			accountDetails.setInterestAccrual(new BigDecimal(0));
			accountDetails.setBalance(accountDetails.getBalance().add(accrual));
		}
		accountMapper.save(accountDetails);
		tso = "Monthly Interest" + accrual;
		util.saveHist(accountDetails, tso, "Monthly Interest Amount", accrual);
		
		logger.debug("Monthly Interest");
		publisher.publishEvent(accountDetails);
		
		return accountDetails;
	}
	
}
