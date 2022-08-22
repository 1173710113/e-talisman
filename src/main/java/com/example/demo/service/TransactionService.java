package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Transaction;

public interface TransactionService {

	
	/**
	 * 
	 * @param fromOpenId
	 * @return
	 */
	public Transaction newTransaction(String fromOpenId, String type, String data, String orderId, String txHash);
	
	
	/**
	 * 
	 * @param openId
	 * @return
	 */
	public List<Transaction> selectUserTransaction(String openId);
}
