package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.TalismanIPToken;

import io.ipfs.api.MerkleNode;

public interface AntChainTalismanService {
	
	
	public MerkleNode saveTalismanInfo(MultipartFile file, String function, AccessPaymentPlan accessPaymentPlan, Integer totalSupply);
	/**
	 * 查找用户拥有的符
	 * @param credentials 用户的登入凭证
	 * @return 用户拥有的符的集合
	 */
	public List<TalismanIPToken> selectTalismanByOwner(String openId);
	
	
	/**
	 *  查找所有的符
	 * @param credentials 用户的登入凭证
	 * @return
	 */
	public List<TalismanIPToken> selectAllTalisman();
	
	
	/**
	 * 查找指定id的符
	 * @param id
	 * @return
	 */
	public TalismanIPToken selectTalismanById(BigInteger id);
	
}
