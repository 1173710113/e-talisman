package com.example.demo.controller;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;

import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionType;
import com.example.demo.domain.User;
import com.example.demo.event.TokenUploadEvent;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.ArtService;
import com.example.demo.service.TransactionService;
import com.example.demo.util.ContractUtil;

import io.ipfs.api.MerkleNode;
import io.swagger.annotations.ApiOperation;

public class ArtController {

	@Autowired
	private ArtService artService;

	@Autowired
	private ContractService contractService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private ContractUtil contractUtil;

	private static Logger logger = LoggerFactory.getLogger(ArtController.class);

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
	@PostMapping(value="/upload", headers = "content-type=multipart/form-data")
	public Response<Transaction> upload(MultipartFile[] files, Integer monthlyPayment, Integer yearlyPayment,
			Integer fullyPayment) throws Exception {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		AccessPaymentPlan accessPaymentPlan = new AccessPaymentPlan(monthlyPayment, yearlyPayment, fullyPayment);
		logger.info(accessPaymentPlan.toString());
		MerkleNode node = artService.saveArtInfo(Arrays.asList(files), accessPaymentPlan);
		logger.info("FILEHASH:" + node.hash.toBase58());
		String orderId = UUID.randomUUID().toString();
		Transaction transaction = transactionService.newTransaction(user.getOpenId(), TransactionType.NEWART, node.hash.toBase58(), orderId, "");
		logger.info("TRANSACTION:" + transaction.toString());
		TokenUploadEvent tokenUploadEvent = new TokenUploadEvent(this, transaction);
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		contractService.uploadIPToken(node.hash.toBase58(), credentials, tokenUploadEvent);
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
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		return ResponseGenerator.successResponse(artService.selectIPTokenByOwner(credentials));
	}

	/**
	 * 
	 * @param address
	 * @return
	 */
	@ApiOperation(value = "查找所有模板")
	@PostMapping("/query/all")
	public Response<List<ArtIPToken>> selectAllArtIPToken() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		return ResponseGenerator.successResponse(artService.selectAllIPToken(credentials));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查找指定ID的模板")
	@PostMapping("/query/id")
	public Response<ArtIPToken> selectArtIPTokenById(BigInteger id) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		return ResponseGenerator.successResponse(artService.selectArtIPTokenById(credentials, id));
	}

}
