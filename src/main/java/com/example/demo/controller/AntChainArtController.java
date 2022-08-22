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
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionType;
import com.example.demo.domain.User;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.AntChainArtService;
import com.example.demo.service.TransactionService;

import io.ipfs.api.MerkleNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "模板接口")
@RestController
@RequestMapping("/art")
public class AntChainArtController {

	@Autowired
	private AntChainArtService antChainArtService;

	@Autowired
	private AntBlockChainService antBlockChainService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AntChainConfig antChainConfig;

	private static Logger logger = LoggerFactory.getLogger(AntChainArtController.class);

	/**
	 * 
	 * @param files
	 * @param monthlyPayment
	 * @param yearlyPayment
	 * @param fullyPayment
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "铸造模板")
	@PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
	public Response<Transaction> upload(List<MultipartFile> files, Integer monthlyPayment, Integer yearlyPayment,
			Integer fullyPayment, Integer totalSupply) throws Exception {
		// 获取请求用户
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		// 生成支付方案
		AccessPaymentPlan accessPaymentPlan = new AccessPaymentPlan(monthlyPayment, yearlyPayment, fullyPayment);
		// 上传文件到ipfs
		MerkleNode node = antChainArtService.saveArtInfo(files, accessPaymentPlan, totalSupply);
		logger.info("FILEHASH:" + node.hash.toBase58());
		String orderId = UUID.randomUUID().toString();
		// 上传符到区块链，并通过事件在符上传成功后修改交易状态
		BaseResp resp = antBlockChainService.newIP(orderId, node.hash.toBase58(), user.getBlockChainName(),
				antChainConfig.getArtTipVotingPower(), BigInteger.valueOf(0),
				antChainConfig.getArtCreatorVotingPower());
		// 生成交易
		ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
		Transaction transaction = transactionService.newTransaction(user.getOpenId(), TransactionType.NEWART,
				node.hash.toBase58(), orderId, txReceipt.getHash());
		logger.info("TRANSACTION:" + transaction.toString());
		return ResponseGenerator.successResponse(transaction);

	}

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "查找用户有的模板")
	@PostMapping("/query/user")
	public Response<List<ArtIPToken>> selectArtIPTokenByOwner() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return ResponseGenerator.successResponse(antChainArtService.selectIArtByOwner(user.getOpenId()));
	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	@ApiOperation(value = "查找所有模板")
	@PostMapping("/query/all")
	public Response<List<ArtIPToken>> selectAllArtIPToken() {
		return ResponseGenerator.successResponse(antChainArtService.selectAllArt());
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查找指定ID的模板")
	@PostMapping("/query/id")
	public Response<ArtIPToken> selectArtIPTokenById(BigInteger id) {
		return ResponseGenerator.successResponse(antChainArtService.selectArtById(id));
	}

}
