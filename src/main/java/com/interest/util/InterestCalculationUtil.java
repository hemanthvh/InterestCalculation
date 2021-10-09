package com.interest.util;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class InterestCalculationUtil implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public BigDecimal dailyInterestArrucal(BigDecimal balance, BigDecimal interestRate) {
		
		BigDecimal intValue = (interestRate.multiply(balance).divide(BigDecimal.valueOf(36500),5));
		
		return intValue;
	}

}
