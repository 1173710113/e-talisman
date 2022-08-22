package com.example.demo.service;

import java.math.BigInteger;

import com.example.demo.domain.Order;
import com.example.demo.domain.User;

public interface OrderService {

	public Order addOrder(BigInteger talismanId, BigInteger artId, Integer total, String description, User buyer, BigInteger talismanAmount, BigInteger artAmount, Integer talismanPriceCent, Integer artPriceCent, String paymentPlan);
	
	public Order selectById(String id);
	
	public void updateById(Order order);
}
