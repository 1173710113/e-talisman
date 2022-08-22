package com.example.demo.service;

import org.web3j.abi.datatypes.Address;

public interface WalletService {

	/**
	 * 查看用户拥有的代币量
	 * @param address 用户的账户地址
	 * @return 拥有的代币量
	 */
	public int balanceOf(Address address);
	
	/**
	 * 获得功德
	 * @param num 功德量
	 */
	public void getEBF(int num);
}
