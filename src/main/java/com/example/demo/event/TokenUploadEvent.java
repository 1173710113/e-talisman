package com.example.demo.event;

import org.springframework.context.ApplicationEvent;

import com.example.demo.contract.Work.IpTokenEventResponse;
import com.example.demo.domain.Transaction;

public class TokenUploadEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Transaction transaction;
	
	private IpTokenEventResponse response;
	
	public TokenUploadEvent(Object source, Transaction transaction) {
		super(source);
		this.transaction = transaction;
		// TODO Auto-generated constructor stub
	}

	public Transaction getTransaction() {
		return transaction;
	}
	

	public IpTokenEventResponse getResponse() {
		return response;
	}

	public void setResponse(IpTokenEventResponse response) {
		this.response = response;
	}
	
	
	
	

}
