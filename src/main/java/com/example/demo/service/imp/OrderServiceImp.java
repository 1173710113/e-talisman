package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImp implements OrderService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImp.class);
	
	@Autowired
	private OrderMapper orderMapper;
	
	private String makeUUID(int len) {
		return UUID.randomUUID().toString().replaceAll("-", "").substring(0, len);
	}

	@Override
	public Order selectById(String id) {
		return orderMapper.selectById(id);
	}

	@Override
	@Transactional
	public Order addOrder(BigInteger talismanId, BigInteger artId, Integer total, String description, User buyer,
			BigInteger talismanAmount, BigInteger artAmount, Integer talismanPriceCent, Integer artPriceCent,
			String paymentPlan) {
		String orderId = makeUUID(32);
		logger.info("OutTradeNo:"+orderId);
		Order order = new Order();
		order.setId(orderId);
		order.setCreatedTime(new Date());
		order.setIsDone(false);
		order.setFlag(false);
		order.setTalismanId(talismanId);
		order.setArtId(artId);
		order.setBuyer(buyer);
		orderMapper.insert(order);
		return order;
	}

	@Override
	@Transactional
	public void updateById(Order order) {
		// TODO Auto-generated method stub
		orderMapper.UpdateById(order);
	}


}
