package com.interest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="identification")
	private long identification;
	
	@Column(name = "branchCode")
	private String branchCode;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "openingDate")
	private Date openingDate;
	
	@Column(name = "balance", columnDefinition="Decimal(10,5)" )
	private BigDecimal balance;
	
	@Column(name = "closingDate")
	private Date closingDate;
	
	@Column(name = "interestAccrual", columnDefinition="Decimal(10,5)")
	private BigDecimal interestAccrual;
	
	@Column(name = "interestRate",columnDefinition="Decimal(10,5)")
	private BigDecimal interestRate;

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public BigDecimal getInterestAccrual() {
		return interestAccrual;
	}

	public void setInterestAccrual(BigDecimal interestAccrual) {
		this.interestAccrual = interestAccrual;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}
	
}
