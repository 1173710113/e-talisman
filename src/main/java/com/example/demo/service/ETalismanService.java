package com.example.demo.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;

import com.example.demo.domain.ETalisman;

public interface ETalismanService {


	/**
	 * 铸造云祝符
	 * @param artIPTokenID 艺术资产IP的tokenID
	 * @param talismanIPTokenID 符IP的tokenID
	 * @param expirationTime 失效时间
	 * @param openId 铸造者的openid
	 * @param paymentPlan 支付方式：法币、代币
	 * @return 铸造的云祝福的tokenID
	 */
	public BigInteger newETalisman(String openId, Date expireTime, BigInteger talismanId, BigInteger artId);
	
	public List<ETalisman> selectByUser(Credentials credentials);
	
	public ETalisman selectById(Credentials credentials, BigInteger id);
	
	/**
	 * 购买云祝符NFT
	 * @param eTalismanTokenID 云祝福NFT的tokenID
	 * @param address 购买者的账户地址
	 * @param paymentPlan 支付方式：法币、代币
	 * @return
	 */
	 //TODO throw exception if user cannot afford
	public void buyETalisman(int eTalismanTokenID, Address address, String paymentPlan);
	
	
	/**
	 * 赠与云祝符NFT
	 * @param eTalismanID 云祝符NFT的tokenID
	 * @param fromOpenId 赠与者的openId
	 * @param toOpenId 受赠者的openId
	 * @param message 留言
	 */
	//TODO 如果赠送者没有该云祝符，则抛出异常
	public void transferETalisman(String fromOpenId,String toOpenId, BigInteger etalismanId, String message);
	
	
	/**
	 * 查看用户拥有的云祝符
	 * @param address 用户的账户地址
	 * @return 用户拥有的所有的云祝符的列表
	 */
	public List<ETalisman> selectETalismanByOwner(Address address);
	
	
	
}
