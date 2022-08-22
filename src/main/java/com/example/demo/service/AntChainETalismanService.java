package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;

import com.example.demo.domain.ETalisman;

public interface AntChainETalismanService {
	
	public List<ETalisman> selectByUser(String openId);
	
	public ETalisman selectById(BigInteger id);
	
	
}
