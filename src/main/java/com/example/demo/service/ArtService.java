package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;

import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.ETalisman;

import io.ipfs.api.MerkleNode;

public interface ArtService {

	public MerkleNode saveArtInfo(List<MultipartFile> files, AccessPaymentPlan accessPaymentPlan);

	/**
	 * 出售艺术资产NFT
	 * 
	 * @param ArtTokenID  艺术资产NFT的tokenID
	 * @param address     发起请求的账户地址
	 * @param paymentPlan 支付方案：代币支付，法币支付
	 * @precondition 发起者持有该艺术资产NFT
	 * @exception 如果发起者不持有该艺术资产NFT，抛出异常
	 */
	public void sellArt(int ArtTokenID, String address, Map<String, Integer> paymentPlan);

	/**
	 * 购买艺术资产NFT的IP
	 * 
	 * @param ArtTokenID  购买的艺术资产的tokenID
	 * @param address     购买者的账户地址
	 * @param paymentPlan 支付方式：法币、代币
	 * @exception 如果购买者的账户余额不足则抛出异常
	 */
	public void buyArtByToken(int ArtTokenID, String address, String paymentPlan);

	/**
	 * 查找所有在售的艺术资产
	 * 
	 * @return 所有在售的艺术资产
	 */
	public List<ArtIPToken> selectArtIPForSale();

	/**
	 * 查找用户所持有的艺术资产
	 * 
	 * @param address 用户的账户地址
	 * @return 艺术资产
	 */
	public List<ArtIPToken> selectIPTokenByOwner(Credentials credentials);

	public List<ArtIPToken> selectAllIPToken(Credentials credentials);

	public ArtIPToken selectArtIPTokenById(Credentials credentials, BigInteger id);

	/**
	 * 查看所有使用该艺术资产的云祝符
	 * 
	 * @param artTokenID 艺术资产的tokenID
	 * @param address    用户的账户地址，必须是该艺术资产的拥有者
	 * @return 使用该艺术资产的云祝符
	 */
	public List<ETalisman> selectETalismanToken(int artTokenID, String address);

	/**
	 * 查看艺术资产对其持有者产生的代币收益
	 * 
	 * @param artToken 艺术资产的tokenID
	 * @param address  持有者的账户地址
	 * @return 代币收益
	 */
	public int tokenProfit(int artToken, String address);

	/**
	 * 查看艺术资产对其持有者产生的法币收益
	 * 
	 * @param artToken 艺术资产的tokenID
	 * @param address  持有者的账户地址
	 * @return 法币收益
	 */
	public float curencyProfit(int artToken, String address);

}
