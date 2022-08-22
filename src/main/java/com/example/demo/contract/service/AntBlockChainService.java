package com.example.demo.contract.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.mychain.sdk.common.VMTypeEnum;
import com.antfinancial.mychain.baas.tool.restclient.RestClient;
import com.antfinancial.mychain.baas.tool.restclient.RestClientProperties;
import com.antfinancial.mychain.baas.tool.restclient.model.CallRestBizParam;
import com.antfinancial.mychain.baas.tool.restclient.model.Method;
import com.antfinancial.mychain.baas.tool.restclient.response.BaseResp;
import com.example.demo.config.AntChainConfig;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;

@Service
public class AntBlockChainService {

	@Autowired
	private RestClient restClient;

	@Autowired
	private RestClientProperties restClientProperties;

	@Autowired
	private AntChainConfig antChainConfig;

	private static Logger logger = LoggerFactory.getLogger(AntBlockChainService.class);

	public BaseResp callContract(String orderId, String contractName, String method, String inputs, String outTypes,
			String account, String kmsId) throws Exception {
		logger.info("orderId" + orderId);
		logger.info("contractName:" + contractName);
		logger.info("method:" + method);
		logger.info("inputs:" + inputs);
		logger.info("outTypes:" + outTypes);
		CallRestBizParam callRestBizParam = CallRestBizParam.builder().orderId(orderId)
				.bizid(restClientProperties.getBizid()).account(account).contractName(contractName)
				.methodSignature(method).inputParamListStr(inputs).outTypes(outTypes)// 合约返回值类型
				.mykmsKeyId(kmsId).method(Method.CALLCONTRACTBIZASYNC).tenantid(restClientProperties.getTenantid())
				.gas(5000000L).build();
		BaseResp resp = restClient.bizChainCallWithReceipt(callRestBizParam);
		if (!resp.isSuccess()) {
			logger.error("EVM合约执行失败: " + resp.getCode() + ", " + resp.getData());
			throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
		}
		if("211".equals(resp.getCode())){
			do {
				logger.info("EVM合约执行未成功: " + resp.getCode());
				Thread.sleep(1000);
				resp = restClient.bizChainCallWithReceipt(callRestBizParam);
			} while ("211".equals(resp.getCode()));
		}
		if ("200".equals(resp.getCode())) {
			logger.info("EVM合约执行成功");
		} else {
			logger.info("EVM合约执行未成功: " + resp.getCode());
			logger.info(JSON.toJSONString(resp));
			throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
		}

		// 异步交易未成功需要根据状态码判断交易状态

		logger.info(JSON.toJSONString(resp));
		return resp;
	}

	public BaseResp callContract(String orderId, String contractName, String method, String inputs, String outTypes)
			throws Exception {
		return callContract(orderId, contractName, method, inputs, outTypes, restClientProperties.getAccount(),
				restClientProperties.getKmsId());
	}

	public BaseResp newIP(String orderId, String uri, String creator, BigInteger tipVotingPower,
			BigInteger tianshifuVotingPower, BigInteger creatorVotingPower) throws Exception {
		String encodedCreator = encodedAddress(creator);
		String method = "newIP(string,uint256[],identity)";
		String outTypes = "[\"uint256\",\"uint256\",\"uint256\"]";
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(uri);
		JSONArray amounts = new JSONArray();
		amounts.add(creatorVotingPower);
		amounts.add(tipVotingPower);
		amounts.add(tianshifuVotingPower);
		jsonArray.add(amounts);
		JSONObject account = encodedAddressFormat(encodedCreator);
		jsonArray.add(account);
		BaseResp resp = callContract(orderId, antChainConfig.getServiceContractName(), method, jsonArray.toJSONString(),
				outTypes);
		return resp;

	}

	public BaseResp BuyETalisman(String orderId, BigInteger talismanId, BigInteger artId, BigInteger talismanAmount,
			BigInteger artAmount, String buyerBlockChainName) throws Exception {
		String encodedAddress = encodedAddress(buyerBlockChainName);
		String method = "buyEtalisman(uint256,uint256,uint256,uint256,identity)";
		String outTypes = "[\"uint256\",\"uint256\"]";
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(talismanId);
		jsonArray.add(artId);
		jsonArray.add(talismanAmount);
		jsonArray.add(artAmount);
		JSONObject account = encodedAddressFormat(encodedAddress);
		jsonArray.add(account);
		BaseResp resp = callContract(orderId, antChainConfig.getServiceContractName(), method, jsonArray.toJSONString(),
				outTypes);
		return resp;
	}

	public BaseResp transferCommodity(String orderId, BigInteger commodityId, String fromBlockChainName,
			String toBlockChainName, String kmsId) throws Exception {
		String encodedFromAddress = encodedAddress(fromBlockChainName);
		String encodedToAddress = encodedAddress(toBlockChainName);
		String outTypes = "[]";
		String method = "safeTransferFrom(identity,identity,uint256)";
		JSONArray jsonArray = new JSONArray();
		JSONObject fromAccount = encodedAddressFormat(encodedFromAddress);
		JSONObject toAccount = encodedAddressFormat(encodedToAddress);
		jsonArray.add(fromAccount);
		jsonArray.add(toAccount);
		jsonArray.add(commodityId);
		BaseResp resp = callContract(orderId, antChainConfig.getCommodityContractName(), method, jsonArray.toJSONString(),
				outTypes, fromBlockChainName, kmsId);
		return resp;
	}

	private JSONObject encodedAddressFormat(String encodedAddress) {
		JSONObject account = new JSONObject();
		account.put("data", encodedAddress);
		account.put("empty", false);
		account.put("value", encodedAddress);
		return account;
	}

	public String encodedAddress(String name) throws UnsupportedEncodingException {
		byte[] bytes = name.getBytes("utf-8");
		byte[] hex = DigestUtils.sha256(bytes);
		byte[] b = Base64.encode(hex);
		return new String(b);
	}

	public String address(String name) throws UnsupportedEncodingException {
		byte[] bytes = name.getBytes("utf-8");
		return DigestUtils.sha256Hex(bytes);
	}

	public BaseResp queryReceipt(String hash) throws Exception {
		BaseResp queryBaseResp = restClient.chainCall(hash, restClientProperties.getBizid(), "", Method.QUERYRECEIPT);
		return queryBaseResp;
	}

	public BaseResp queryOutput(String hash, String outTypes) throws Exception {
		BaseResp queryBaseResp = queryReceipt(hash);
		assert (queryBaseResp.isSuccess());
		String s = queryBaseResp.getData();
		String output = s.substring(s.indexOf("output") + 9, s.indexOf("result")).replace("\"", "").replace(",", "");
		if(output.length() != 0) {
			byte[] content = Hex.encode(Base64.decode(output));
			CallRestBizParam callRestBizParam = CallRestBizParam.builder().bizid(restClientProperties.getBizid())
					.method(Method.PARSEOUTPUT).tenantid(restClientProperties.getTenantid())
					.orderId("order_" + System.currentTimeMillis()).vmTypeEnum(VMTypeEnum.EVM).content(new String(content))
					.abi(outTypes).mykmsKeyId(restClientProperties.getKmsId()).build();
			BaseResp baseResp = restClient.chainCallForBiz(callRestBizParam);
			assert (baseResp.isSuccess());
			return baseResp;
		} else {
			return queryBaseResp;
		}
		
	}

	public void creatAccount(String account, String kmsId) throws Exception {
		CallRestBizParam param = CallRestBizParam.builder().
		// 创建账户方法名称，固定值
				method(Method.TENANTCREATEACCUNT).
				// 本次交易请求ID
				orderId(UUID.randomUUID().toString()).
				// 执行创建账户交易的已有区块链账户
				account(restClientProperties.getAccount()).
				// 执行创建账户交易的已有区块链账户KMS密钥ID
				mykmsKeyId(restClientProperties.getKmsId()).
				// 新建区块链账户 不可重复 包括失败的账户名也不可使用
				newAccountId(account).
				// 新建区块链账户KMS密钥ID 可重复
				newAccountKmsId(kmsId).
				// 确保设置的Gas参数足够大，且执行创建的账户中有足够Gas，并且账户燃料要大于参数数值
				gas(100000L).build();
		BaseResp resp = restClient.chainCallForBiz(param);
		if (!resp.isSuccess()) {
			logger.error("创建账户失败: " + resp.getCode() + ", " + resp.getData());
			throw new BaseException(ExceptionEnum.USER_CREATE_ERROR);
		}
	}
}
