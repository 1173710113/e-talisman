package com.example.demo.service;

import com.example.demo.domain.User;

public interface UserService {

	
	/**
	 * 微信登入，如果第一次登入，自動注冊生成以太坊賬戶
	 * @param code
	 * @param rawData
	 * @param signature
	 * @param encrypteData
	 * @param iv
	 * @return
	 * @throws Exception 
	 */
	public User wxlogin(String code, String rawData, String signature, String encrypteData, String iv) throws Exception;
	
	/**
	 * 通过openId查找用户,未找到抛出异常
	 * @param openId
	 * @return
	 */
	
	public User selectByOpenId(String openId);
	
	
	/**
	 * 若找不到或者查詢到多個結果，抛出異常
	 * @param blockChainAddress 小写
	 * @return
	 */
	public User selectByBlockChainAddress(String blockChainAddress);
	
	
	
}
