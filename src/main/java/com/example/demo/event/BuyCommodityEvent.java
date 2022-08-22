package com.example.demo.event;

import java.math.BigInteger;

import org.springframework.context.ApplicationEvent;

import com.example.demo.contract.Commodity.AfterReleaseEventResponse;
import com.example.demo.domain.Transaction;

public class BuyCommodityEvent extends ApplicationEvent {

	public static final String ART = "art";
	public static final String TALISMAN = "talisman";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Transaction transaction;

	private AfterReleaseEventResponse response;

	private String type;
	
	private BigInteger etalismanId;

	public BuyCommodityEvent(Object source, Transaction transaction, String type, BigInteger etalismanId) {
		super(source);
		this.transaction = transaction;
		this.type = type;
		this.etalismanId = etalismanId;
		// TODO Auto-generated constructor stub
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public AfterReleaseEventResponse getResponse() {
		return response;
	}

	public void setResponse(AfterReleaseEventResponse response) {
		this.response = response;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigInteger getEtalismanId() {
		return etalismanId;
	}

	public void setEtalismanId(BigInteger etalismanId) {
		this.etalismanId = etalismanId;
	}
	
	

}
