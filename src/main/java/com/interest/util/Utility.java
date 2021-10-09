package com.interest.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interest.entity.Account;
import com.interest.entity.AccountHistory;
import com.interest.services.AccountHistoryImpl;

@Component
public class Utility implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountHistoryImpl accountHistoryImpl;

	public void saveHist(Account account, String tso, String operationType) {

		Date today = new Date();

		AccountHistory accountHist = new AccountHistory();
		accountHist.setIdentification(account.getIdentification());
		accountHist.setEndOfDayBalance(account.getBalance());
		accountHist.setOperationType(operationType);
		accountHist.setComments(tso);
		accountHist.setLastMaintainceDate(today);
		accountHistoryImpl.history(accountHist);
	}
	
	public void saveHist(Account account, String tso, String operationType, BigDecimal monthlyInterest) {

		Date today = new Date();

		AccountHistory accountHist = new AccountHistory();
		accountHist.setIdentification(account.getIdentification());
		accountHist.setEndOfDayBalance(account.getBalance());
		accountHist.setOperationType(operationType);
		accountHist.setComments(tso);
		accountHist.setLastMaintainceDate(today);
		accountHist.setMonthlyInterest(monthlyInterest);
		accountHistoryImpl.history(accountHist);
	}

}
