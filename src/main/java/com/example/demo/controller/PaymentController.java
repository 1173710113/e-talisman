package com.example.demo.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.antfinancial.mychain.baas.tool.restclient.model.ReceiptDecoration;
import com.antfinancial.mychain.baas.tool.restclient.response.BaseResp;
import com.example.demo.contract.service.AntBlockChainService;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.Order;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionType;
import com.example.demo.domain.User;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.AntChainArtService;
import com.example.demo.service.AntChainTalismanService;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentService;
import com.example.demo.service.TransactionService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.felord.payment.wechat.enumeration.TradeState;
import cn.felord.payment.wechat.v3.WechatApiProvider;
import cn.felord.payment.wechat.v3.model.ResponseSignVerifyParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "微信支付")
@Controller
@RequestMapping(value = "/wxpay/")
public class PaymentController {
	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private WechatApiProvider wechatApiProvider;

	@Autowired
	private UserService userService;

	@Autowired
	private AntChainTalismanService antChainTalismanService;

	@Autowired
	private AntChainArtService antChainArtService;

	@Autowired
	private AntBlockChainService antBlockChainService;

	@Autowired
	private TransactionService transactionService;

	/**
	 * 
	 * @param talismanId
	 * @param artId
	 * @param paymentPlan
	 * @return
	 */
	@ApiOperation(value = "购买云祝福")
	@ResponseBody
	@PostMapping("/prepay/etalisman")
	@ApiImplicitParams({ @ApiImplicitParam(name = "talismanId", value = "购买的符ID"),
			@ApiImplicitParam(name = "artId", value = "购买的符ID"),
			@ApiImplicitParam(name = "paymentPlan", value = "monthlyPayment/yearlyPayment/fullyPayment") })
	@Transactional
	public Response<ObjectNode> etalismanPrepay(BigInteger talismanId, BigInteger artId, String paymentPlan) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();

		logger.info(String.format("Prepay[Buyer:%s,talismanId:%s,artId:%s,paymentPlan:%s]", user.toString(), talismanId,
				artId, paymentPlan));
		TalismanIPToken talismanIPToken = antChainTalismanService.selectTalismanById(talismanId);
		ArtIPToken artIPToken = antChainArtService.selectArtById(artId);
		assert (talismanIPToken.getRemianSupply() != 0);
		assert (artIPToken.getRemianSupply() != 0);
		Integer total = talismanIPToken.getAccessPaymentPlan().getPayment(paymentPlan)
				+ artIPToken.getAccessPaymentPlan().getPayment(paymentPlan);
		String description = "购买云祝福";
		Integer talismanPriceCent = talismanIPToken.getAccessPaymentPlan().getPayment(paymentPlan);
		Integer artPriceCent = artIPToken.getAccessPaymentPlan().getPayment(paymentPlan);
		BigInteger halfDecimal = BigInteger.valueOf((long) Math.pow(10, 9));
		BigInteger decimal = halfDecimal.multiply(halfDecimal);
		BigInteger talismanAmount = BigInteger.valueOf(talismanPriceCent).multiply(decimal);
		BigInteger artAmount = BigInteger.valueOf(artPriceCent).multiply(decimal);
		String outTradeNo = orderService.addOrder(talismanId, artId, total, description, user, talismanAmount,
				artAmount, talismanPriceCent, artPriceCent, paymentPlan).getId();
		String openId = user.getOpenId();
		return ResponseGenerator.successResponse(paymentService.prePay(total, openId, description, outTradeNo));
	}

	/**
	 * 
	 * @param wechatpaySerial
	 * @param wechatpaySignature
	 * @param wechatpayTimestamp
	 * @param wechatpayNonce
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ApiIgnore
	@PostMapping("/callback")
	@Transactional
	public Map<String, ?> transactionCallback(@RequestHeader("Wechatpay-Serial") String wechatpaySerial,
			@RequestHeader("Wechatpay-Signature") String wechatpaySignature,
			@RequestHeader("Wechatpay-Timestamp") String wechatpayTimestamp,
			@RequestHeader("Wechatpay-Nonce") String wechatpayNonce, HttpServletRequest request) throws IOException {
		String body = request.getReader().lines().collect(Collectors.joining());
		// 对请求头进行验签 以确保是微信服务器的调用
		ResponseSignVerifyParams params = new ResponseSignVerifyParams();
		params.setWechatpaySerial(wechatpaySerial);
		params.setWechatpaySignature(wechatpaySignature);
		params.setWechatpayTimestamp(wechatpayTimestamp);
		params.setWechatpayNonce(wechatpayNonce);
		params.setBody(body);
		String tenantId = "miniapp";
		return wechatApiProvider.callback(tenantId).transactionCallback(params, data -> {
			// TODO 对回调解析的结果进行消费 需要保证消费的幂等性 微信有可能多次调用此接口
			User user = userService.selectByOpenId(data.getPayer().getOpenid());
			Order order = orderService.selectById(data.getOutTradeNo());
			if (!order.getIsDone()) {
				TradeState tradeState = data.getTradeState();
				if (tradeState.equals(TradeState.SUCCESS)) {
					order.setIsDone(true);
					order.setFlag(true);
					order.setFinishTime(new Date());
					assert (data.getAmount().getTotal().equals(order.getTotal()));
					orderService.updateById(order);
					Integer talismanPriceCent = order.getTalismanPriceCent();
					Integer artPriceCent = order.getArtPriceCent();
					BigInteger talismanAmount = order.getTalismanAmount();
					BigInteger artAmount = order.getArtAmount();
					String orderId = UUID.randomUUID().toString();
					BigInteger talismanId = order.getTalismanId();
					BigInteger artId = order.getArtId();
					try {
						BaseResp resp = antBlockChainService.BuyETalisman(orderId, talismanId, artId, talismanAmount,
								artAmount, user.getBlockChainName());
						ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
						JSONObject object = new JSONObject();
						object.put("talismanId", talismanId);
						object.put("artId", artId);
						object.put("talismanAmount", talismanAmount);
						object.put("artAmount", artAmount);
						object.put("talismanPriceCent", talismanPriceCent);
						object.put("artPriceCent", artPriceCent);
						object.put("paymentPlan", order.getPaymentPlan());
						transactionService.newTransaction(user.getOpenId(), TransactionType.NEWETALISMAN,
								object.toJSONString(), orderId, txReceipt.getHash());
					} catch (Exception e) {
						e.printStackTrace();
						throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
					}
				} else if (tradeState.equals(TradeState.USERPAYING)) {
					order.setIsDone(true);
					order.setFlag(false);
					order.setFinishTime(new Date());
					orderService.updateById(order);
				}
			}
		});
	}

}
