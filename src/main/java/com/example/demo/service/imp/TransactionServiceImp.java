package com.example.demo.service.imp;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.demo.domain.Transaction;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.service.TransactionService;

@Service
public class TransactionServiceImp implements TransactionService{

	@Autowired
	private TransactionMapper transactionMapper;
	
	private static Logger logger = LoggerFactory.getLogger(TransactionServiceImp.class);
	
	@Override
	public Transaction newTransaction(String from, String type, String data, String orderId, String txHash) {
		Transaction transaction = new Transaction();
		transaction.setFrom(from);
		transaction.setCreatedTime(new Date());
		transaction.setFlag(false);
		transaction.setDone(false);
		transaction.setType(type);
		transaction.setData(data);
		transaction.setId(orderId);
		transaction.setTxHash(txHash);
		transactionMapper.insert(transaction);
		return transaction;
	}
	

	@Override
	public List<Transaction> selectUserTransaction(String address) {
		Transaction condition = new Transaction();
		condition.setFrom(address);
		logger.info(JSON.toJSONString(condition));
		return transactionMapper.select(condition);
	}

}
