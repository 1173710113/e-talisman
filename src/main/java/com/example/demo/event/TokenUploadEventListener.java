package com.example.demo.event;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.demo.contract.Work.IpTokenEventResponse;
import com.example.demo.domain.Transaction;
import com.example.demo.mapper.TransactionMapper;

@Component
public class TokenUploadEventListener implements ApplicationListener<TokenUploadEvent>{
 
	private static Logger logger = LoggerFactory.getLogger(TokenUploadEventListener.class);
	
	@Autowired
	private TransactionMapper transactionMapper;
	
	@Override
	public void onApplicationEvent(TokenUploadEvent event) {
		Transaction transaction = event.getTransaction();
		transaction.setDone(true);
		transaction.setFinishTime(new Date());
		transactionMapper.updateById(transaction);
		logger.info(transaction.toString());
		if(transaction.isFlag()) {
			IpTokenEventResponse response = event.getResponse();
			logger.info(JSON.toJSONString(response));
		}
		
	}

}
