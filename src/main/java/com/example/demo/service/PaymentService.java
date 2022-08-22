package com.example.demo.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface PaymentService {
	
	public ObjectNode prePay(Integer total, String openId, String description, String outTradeNo);
}