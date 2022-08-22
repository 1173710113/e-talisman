package com.example.demo.service;

import com.example.demo.domain.IPToken;

public interface IPService {
	
	public IPToken mintIP(String fileHash, String uploaderAddress);
}
