package com.example.demo.controller;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.antfinancial.mychain.baas.tool.restclient.model.ReceiptDecoration;
import com.antfinancial.mychain.baas.tool.restclient.response.BaseResp;
import com.example.demo.contract.service.AntBlockChainService;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionType;
import com.example.demo.domain.User;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.AntChainArtService;
import com.example.demo.service.AntChainETalismanService;
import com.example.demo.service.AntChainTalismanService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "云祝符接口")
@RestController
@RequestMapping("/etalisman")
public class AntChainETalismanController {

	private static Logger logger = LoggerFactory.getLogger(AntChainETalismanController.class);

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AntChainETalismanService antChainETalismanService;

	@Autowired
	private AntBlockChainService antBlockChainService;

	@Autowired
	private AntChainTalismanService antChainTalismanService;

	@Autowired
	private AntChainArtService antChainArtService;

	@Autowired
	private UserService userService;

	/**
	 * 
	 * @param talismanId
	 * @param artId
	 * @param expireTime
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "购买云祝福")
	@PostMapping("/buy")
	public Response<Transaction> createETalisman(BigInteger talismanId, BigInteger artId, String paymentPlan)
			throws Exception {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		String openId = user.getOpenId();
		String orderId = UUID.randomUUID().toString();
		TalismanIPToken talismanIPToken = antChainTalismanService.selectTalismanById(talismanId);
		ArtIPToken artIPToken = antChainArtService.selectArtById(artId);
		assert (talismanIPToken.getRemianSupply() != 0);
		assert (artIPToken.getRemianSupply() != 0);
		Integer talismanPriceCent = talismanIPToken.getAccessPaymentPlan().getPayment(paymentPlan);
		Integer artPriceCent = artIPToken.getAccessPaymentPlan().getPayment(paymentPlan);
		BigInteger halfDecimal = BigInteger.valueOf((long) Math.pow(10, 9));
		BigInteger decimal = halfDecimal.multiply(halfDecimal);
		BigInteger talismanAmount = BigInteger.valueOf(talismanPriceCent).multiply(decimal);
		BigInteger artAmount = BigInteger.valueOf(artPriceCent).multiply(decimal);
		BaseResp resp = antBlockChainService.BuyETalisman(orderId, talismanId, artId, talismanAmount, artAmount,
				user.getBlockChainName());
		ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
		JSONObject object = new JSONObject();
		object.put("talismanId", talismanId);
		object.put("artId", artId);
		object.put("talismanAmount", talismanAmount);
		object.put("artAmount", artAmount);
		object.put("talismanPriceCent", talismanPriceCent);
		object.put("artPriceCent", artPriceCent);
		object.put("paymentPlan", paymentPlan);
		Transaction transaction = transactionService.newTransaction(openId, TransactionType.NEWETALISMAN, object.toJSONString(),
				orderId, txReceipt.getHash());
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
		return ResponseGenerator.successResponse(antChainETalismanService.selectByUser(user.getOpenId()));
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "查找指定ID的云祝福")
	@PostMapping("/query/id")
	public Response<ETalisman> selectById(BigInteger id) {
		return ResponseGenerator.successResponse(antChainETalismanService.selectById(id));
	}
	
	@PostMapping("/transfer")
	public Response<List<Transaction>> transferETalisman(String toOpenId, BigInteger etalismanId) throws Exception {
		User from = (User) SecurityUtils.getSubject().getPrincipal();
		ETalisman etalisman = antChainETalismanService.selectById(etalismanId);
		assert (etalisman != null);
		assert (etalisman.getOwner().equals(from));
		User to = userService.selectByOpenId(toOpenId);
		assert (to != null);
		String transferTalismanCommodityOrderId = UUID.randomUUID().toString();
		BaseResp transferTalismanCommodityResp = antBlockChainService.transferCommodity(
				transferTalismanCommodityOrderId, etalisman.getTalismanCommodityId(), from.getBlockChainName(),
				to.getBlockChainName(), from.getPrivateKey());
		ReceiptDecoration transferTalismanCommodityTxReceipt = JSON.parseObject(transferTalismanCommodityResp.getData(),
				ReceiptDecoration.class);
		String transferArtCommodityOrderId = UUID.randomUUID().toString();
		BaseResp transferArtCommodityResp = antBlockChainService.transferCommodity(transferArtCommodityOrderId,
				etalisman.getArtCommodityId(), from.getBlockChainName(), to.getBlockChainName(), from.getPrivateKey());
		ReceiptDecoration transferArtCommodityTxReceipt = JSON.parseObject(transferArtCommodityResp.getData(),
				ReceiptDecoration.class);
		JSONObject object = new JSONObject();
		object.put("etalismanId", etalismanId);
		object.put("toOpenId", toOpenId);
		Transaction transferTalismanCommodityTransaction = transactionService.newTransaction(from.getOpenId(),
				"TRANSFERTALISMANCOMMODITY", object.toJSONString(), transferTalismanCommodityOrderId,
				transferTalismanCommodityTxReceipt.getHash());
		Transaction transferArtCommodityTransaction = transactionService.newTransaction(from.getOpenId(),
				"TRANSFERARTCOMMODITY", object.toJSONString(), transferArtCommodityOrderId,
				transferArtCommodityTxReceipt.getHash());
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transferTalismanCommodityTransaction);
		transactions.add(transferArtCommodityTransaction);
		return ResponseGenerator.successResponse(transactions);
	}

}
