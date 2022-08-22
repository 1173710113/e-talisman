package com.example.demo.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.antfinancial.mychain.baas.tool.restclient.model.ReceiptDecoration;
import com.antfinancial.mychain.baas.tool.restclient.response.BaseResp;
import com.example.demo.config.AntChainConfig;
import com.example.demo.contract.service.AntBlockChainService;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionType;
import com.example.demo.domain.User;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.AntChainTalismanService;
import com.example.demo.service.TransactionService;

import io.ipfs.api.MerkleNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "符接口")
@RestController
@RequestMapping("/talisman")
public class AntChainTalismanController {

	private static Logger logger = LoggerFactory.getLogger(AntChainTalismanController.class);

	@Autowired
	private AntChainTalismanService antChainTalismanService;

	@Autowired
	private AntBlockChainService antBlockChainService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AntChainConfig antChainConfig;

	/**
	 * 上传符，由于上传符是异步的，不能同步返回符的信息，并返回一个上传符的交易
	 * 
	 * @param file           符文件
	 * @param function       符的功效
	 * @param monthlyPayment 月付价格，单位分，CNY
	 * @param yearlyPayment  年付价格，单位分，CNY
	 * @param fullyPayment   全付价格，单位分，CNY
	 * @return 上传符的交易
	 * @throws Exception
	 */
	@ApiOperation(value = "上传符")
	@PostMapping("/upload")
	public Response<Transaction> Upload(MultipartFile file, String function, Integer monthlyPayment,
			Integer yearlyPayment, Integer fullyPayment, Integer totalSupply) throws Exception {
		// 获取请求用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 生成支付方案
		AccessPaymentPlan accessPaymentPlan = new AccessPaymentPlan(monthlyPayment, yearlyPayment, fullyPayment);
		// 上传文件到ipfs
		MerkleNode node = antChainTalismanService.saveTalismanInfo(file, function, accessPaymentPlan, totalSupply);
		logger.info("FILEHASH:" + node.hash.toBase58());
		String orderId = UUID.randomUUID().toString();
		// 上传符到区块链，并通过事件在符上传成功后修改交易状态
		BaseResp resp = antBlockChainService.newIP(orderId, node.hash.toBase58(), user.getBlockChainName(),
				antChainConfig.getTalismanTipVotingPower(), antChainConfig.getTalismanTianshifuVotingPower(),
				antChainConfig.getTalismanCreatorVotingPower());
		// 生成交易
		ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
		Transaction transaction = transactionService.newTransaction(user.getOpenId(), TransactionType.NEWTALISMAN,
				node.hash.toBase58(), orderId, txReceipt.getHash());
		logger.info("TRANSACTION:" + transaction.toString());
		return ResponseGenerator.successResponse(transaction);
	}

	/**
	 * 查找用户拥有的所有符
	 * 
	 * @return
	 */
	@ApiOperation(value = "查找用户拥有的符")
	@PostMapping("/query/user")
	public Response<List<TalismanIPToken>> selectTalismanIPTokenByOwner() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return ResponseGenerator.successResponse(antChainTalismanService.selectTalismanByOwner(user.getOpenId()));
	}

	/**
	 * 查找所有符
	 * 
	 * @return
	 */
	@ApiOperation(value = "查找所有的符")
	@PostMapping("/query/all")
	public Response<List<TalismanIPToken>> selectAllTalismanIPToken() {
		return ResponseGenerator.successResponse(antChainTalismanService.selectAllTalisman());
	}

	/**
	 * 查找指定id的符，
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查找指定ID的符")
	@PostMapping("/query/id")
	public Response<TalismanIPToken> selectTalismanIPTokenById(BigInteger id) {
		return ResponseGenerator.successResponse(antChainTalismanService.selectTalismanById(id));
	}
}
