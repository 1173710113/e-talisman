package com.example.demo.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.example.demo.service.PaymentService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cn.felord.payment.wechat.v3.WechatApiProvider;
import cn.felord.payment.wechat.v3.model.Amount;
import cn.felord.payment.wechat.v3.model.PayParams;
import cn.felord.payment.wechat.v3.model.Payer;

@Service
public class PaymentServiceImp implements PaymentService {

	private static Logger logger = LoggerFactory.getLogger(PaymentServiceImp.class);

	@Autowired
	private WechatApiProvider wechatApiProvider;


	@Override
	public ObjectNode prePay(Integer total, String openId, String description, String outTradeNo) {
		PayParams payParams = new PayParams();
		payParams.setOutTradeNo(outTradeNo);
		payParams.setNotifyUrl("/wxpay/callbacks/transaction");
		payParams.setDescription(description);
		Amount amount = new Amount();
		amount.setTotal(total);
		payParams.setAmount(amount);
		Payer payer = new Payer();
		payer.setOpenid(openId);
		payParams.setPayer(payer);
		logger.info(JSON.toJSONString(payParams));
		return wechatApiProvider.directPayApi("miniapp").jsPay(payParams).getBody();
	}

}
