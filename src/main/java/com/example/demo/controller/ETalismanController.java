package com.example.demo.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.web3j.crypto.Credentials;

import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;
import com.example.demo.event.BuyCommodityEvent;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.ETalismanService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import com.example.demo.util.ContractUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

public class ETalismanController {
	
	private static Logger logger = LoggerFactory.getLogger(ETalismanController.class);

	@Autowired
	private ContractService contractService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ETalismanService eTalismanService;
	
	@Autowired
	private ContractUtil contractUtil;
	
	@Autowired
	private UserService userService;
	

	/**
	 * 
	 * @param talismanId
	 * @param artId
	 * @param expireTime
	 * @return
	 */
	@ApiOperation(value = "购买云祝福")
	@PostMapping("/buy")
	public Response<Transaction> createETalisman(BigInteger talismanId, BigInteger artId,
			Date expireTime) {
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		String openId = user.getOpenId();
		Transaction transaction = transactionService.newTransaction(openId,"", "", "", "");
		BigInteger etalismanId = eTalismanService.newETalisman(openId, expireTime, talismanId, artId);
		BuyCommodityEvent buyArtEvent = new BuyCommodityEvent(this, transaction, BuyCommodityEvent.ART, etalismanId);
		BuyCommodityEvent buyTalismanEvent = new BuyCommodityEvent(this, transaction, BuyCommodityEvent.TALISMAN,
				etalismanId);
		
		
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		contractService.buyWorkCommodity(credentials, artId, buyArtEvent);
		contractService.buyWorkCommodity(credentials, talismanId, buyTalismanEvent);
		return ResponseGenerator.successResponse(transaction);
	}

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "查找用户拥有的云祝福")
	@PostMapping("/query/user")
	public Response<List<ETalisman>> selectByUser() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		return ResponseGenerator.successResponse(eTalismanService.selectByUser(credentials));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查找指定ID的云祝福")
	@PostMapping("/query/id")
	public Response<ETalisman> selectById(BigInteger id) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		return ResponseGenerator.successResponse(eTalismanService.selectById(credentials, id));
	}


	/**
	 * 
	 * @param toOpenId
	 * @param id
	 * @param talismanCommodityId
	 * @param artCommodityId
	 * @param message
	 * @return
	 */
	@ApiOperation(value = "转让云祝福")
	@PostMapping("/transfer")
	public Response<String> transfer(String toOpenId, BigInteger id, BigInteger talismanCommodityId,
			BigInteger artCommodityId, String message) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		User toUser = userService.selectByOpenId(toOpenId);
		String toBlockChainAddress = toUser.getBlockChainAddress();
		contractService.transfer(credentials, toBlockChainAddress, talismanCommodityId);
		contractService.transfer(credentials, toBlockChainAddress, artCommodityId);
		eTalismanService.transferETalisman(user.getOpenId(), toOpenId, id, message);
		return ResponseGenerator.successResponse("success");
	}
}
