package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;

import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.TalismanIPToken;

import io.ipfs.api.MerkleNode;

public interface TalismanService {
	
	
	public MerkleNode saveTalismanInfo(MultipartFile file, String function, AccessPaymentPlan accessPaymentPlan, Integer totalSupply);
	/**
	 * 查找用户拥有的符
	 * @param credentials 用户的登入凭证
	 * @return 用户拥有的符的集合
	 */
	public List<TalismanIPToken> selectTalismanByOwner(Credentials credentials);
	
	
	/**
	 *  查找所有的符
	 * @param credentials 用户的登入凭证
	 * @return
	 */
	public List<TalismanIPToken> selectAllTalisman(Credentials credentials);
	
	
	/**
	 * 查找指定id的符
	 * @param credentials 用户登入凭证，不一定是该符的拥有者
	 * @param id
	 * @return
	 */
	public TalismanIPToken selectTalismanById(Credentials credentials, BigInteger id);
	

	/**
	 * 查看所有使用该符的云祝符
	 * @param talismanTokenID 符的tokenID
	 * @param address 用户的账户地址，必须是该符的拥有者
	 * @return 使用该符的云祝符
	 */
	public List<ETalisman> selectETalismanToken(int talismanTokenID, String address);
	
	/**
	 * 查看艺术资产对其持有者产生的代币收益
	 * @param artToken 艺术资产的tokenID
	 * @param address 持有者的账户地址
	 * @return 代币收益
	 */
	public int tokenProfit(int artToken, String address);
	
	/**
	 * 查看艺术资产对其持有者产生的法币收益
	 * @param artToken 艺术资产的tokenID
	 * @param address 持有者的账户地址
	 * @return 法币收益
	 */
	public float curencyProfit(int artToken, String address);
}
