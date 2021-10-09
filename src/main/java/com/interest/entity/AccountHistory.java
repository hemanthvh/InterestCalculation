package com.interest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.interest.models.AccountHistoryId;

@Entity
@Table (name = "history")
@IdClass(AccountHistoryId.class)
public class AccountHistory implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="identification")
	private long identification;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="histSequence")
	private long histSequence;
	
	@Column(name="operationType")
	private String operationType;
	
	@Column(name="eodbalance",columnDefinition="Decimal(10,5)")
	private BigDecimal endOfDayBalance;
	
	@Column(name="monthlyinterest",columnDefinition="Decimal(10,5)")
	private BigDecimal monthlyInterest;
	
	@Column(name="lstdate")
	private Date lastMaintainceDate;
	
	@Column(name = "tso")
	private String comments;

	public long getIdentification() {
		return identification;
	}

	public void setIdentification(long identification) {
		this.identification = identification;
	}

	public long getHistSequence() {
		return histSequence;
	}

	public void setHistSequence(long histSequence) {
		this.histSequence = histSequence;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public BigDecimal getEndOfDayBalance() {
		return endOfDayBalance;
	}

	public void setEndOfDayBalance(BigDecimal endOfDayBalance) {
		this.endOfDayBalance = endOfDayBalance;
	}

	public BigDecimal getMonthlyInterest() {
		return monthlyInterest;
	}

	public void setMonthlyInterest(BigDecimal monthlyInterest) {
		this.monthlyInterest = monthlyInterest;
	}

	public Date getLastMaintainceDate() {
		return lastMaintainceDate;
	}

	public void setLastMaintainceDate(Date lastMaintainceDate) {
		this.lastMaintainceDate = lastMaintainceDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
