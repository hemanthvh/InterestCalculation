package com.interest.models;

import java.io.Serializable;

public class AccountHistoryId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long identification;
	private long histSequence;
	
	public AccountHistoryId() {
		
	}
	public AccountHistoryId(long identification, long histSequence) {
		this.identification = identification;
		this.histSequence = histSequence;
	}
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
	
}
