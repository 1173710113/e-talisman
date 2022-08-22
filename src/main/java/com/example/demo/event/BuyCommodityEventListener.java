package com.example.demo.event;

import java.math.BigInteger;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.example.demo.contract.Commodity.AfterReleaseEventResponse;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.Transaction;
import com.example.demo.mapper.ETalismanMapper;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;

@Component
public class BuyCommodityEventListener implements ApplicationListener<BuyCommodityEvent> {

	private static Logger logger = LoggerFactory.getLogger(BuyCommodityEventListener.class);

	@Autowired
	private TransactionMapper transactionMapper;
	
	@Autowired
	private ETalismanMapper etalismanMapper;

	@Override
	public void onApplicationEvent(BuyCommodityEvent event) {
		Transaction transaction = event.getTransaction();
		updateTransaction(transaction);
		if (transaction.isFlag()) {
			AfterReleaseEventResponse response = event.getResponse();
			logger.info(JSON.toJSONString(response));
			BigInteger commodityId = response._commodityId;
			String type = event.getType();
			BigInteger etalismanId =event.getEtalismanId();
			updateCommodityId(type, commodityId, etalismanId);
		}

	}
	
	private void updateTransaction(Transaction transaction) {
		transaction.setDone(true);
		transaction.setFinishTime(new Date());
		transactionMapper.updateById(transaction);
		logger.info(transaction.toString());
	}
	
	private void updateCommodityId(String type, BigInteger commodityId, BigInteger etalismanId) {
		ETalisman etalisman = new ETalisman();
		etalisman.setEtalismanId(etalismanId);
		switch (type) {
		case BuyCommodityEvent.ART:
			etalisman.setArtCommodityId(commodityId);
			break;
		case BuyCommodityEvent.TALISMAN:
			etalisman.setTalismanCommodityId(commodityId);
			break;
		default:
			throw new BaseException(ExceptionEnum.AUGUMENT_ERROR);
		}
		logger.info(etalisman.toString());
		etalismanMapper.updateById(etalisman);
	}

}
