package com.example.demo.controller;

import java.math.BigInteger;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;
import com.example.demo.event.TokenUploadEvent;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.TalismanService;
import com.example.demo.service.TransactionService;
import com.example.demo.util.ContractUtil;

import io.ipfs.api.MerkleNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

public class TalismanController {
	
	private static Logger logger = LoggerFactory.getLogger(TalismanController.class);
	
	@Autowired
	private TalismanService talismanService;
	
	@Autowired 
	private ContractService contractService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private ContractUtil contractUtil;
	
	
	/**
	 * 上传符，由于上传符是异步的，不能同步返回符的信息，并返回一个上传符的交易
	 * @param file 符文件	
	 * @param function 符的功效	
	 * @param monthlyPayment 月付价格，单位分，CNY
	 * @param yearlyPayment 年付价格，单位分，CNY
	 * @param fullyPayment 全付价格，单位分，CNY
	 * @return 上传符的交易
	 */
	@ApiOperation(value = "上传符")
	@PostMapping("/upload")
	public Response<Transaction> Upload(MultipartFile file, String function, Integer monthlyPayment, Integer yearlyPayment, Integer fullyPayment, Integer totalSupply){
		//获取请求用户
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		//生成支付方案
		AccessPaymentPlan accessPaymentPlan = new AccessPaymentPlan(monthlyPayment, yearlyPayment, fullyPayment);
		//上传文件到ipfs
		MerkleNode node = talismanService.saveTalismanInfo(file, function, accessPaymentPlan, totalSupply);
		logger.info("FILEHASH:"+node.hash.toBase58());
		//生成交易
		Transaction transaction = transactionService.newTransaction(user.getOpenId(), "", "", "", "");
		logger.info("TRANSACTION:"+transaction.toString());
		//上传符到区块链，并通过事件在符上传成功后修改交易状态
		TokenUploadEvent tokenUploadEvent = new TokenUploadEvent(this, transaction);
		contractService.uploadIPToken(node.hash.toBase58(), contractUtil.getCredentials(user.getPrivateKey()),tokenUploadEvent);
		return ResponseGenerator.successResponse(transaction);
	}
	
	
	/**
	 * 查找用户拥有的所有符
	 * @return
	 */
	@ApiOperation(value = "查找用户拥有的符")
	@PostMapping("/query/user")
	public Response<List<TalismanIPToken>> selectTalismanIPTokenByOwner(){
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		return ResponseGenerator.successResponse(talismanService.selectTalismanByOwner(contractUtil.getCredentials(user.getPrivateKey())));
	}
	
	/**
	 * 查找所有符
	 * @return
	 */
	@ApiOperation(value = "查找所有的符")
	@PostMapping("/query/all")
	public Response<List<TalismanIPToken>> selectAllTalismanIPToken(){
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		return ResponseGenerator.successResponse(talismanService.selectAllTalisman(contractUtil.getCredentials(user.getPrivateKey())));
	}
	
	/**
	 * 查找指定id的符，
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查找指定ID的符")
	@PostMapping("/query/id")
	public Response<TalismanIPToken> selectTalismanIPTokenById(BigInteger id){
		User user = (User)SecurityUtils.getSubject().getPrincipal();
		return ResponseGenerator.successResponse(talismanService.selectTalismanById(contractUtil.getCredentials(user.getPrivateKey()), id));
	}
}
