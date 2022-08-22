package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.mychain.sdk.api.utils.Utils;
import com.alipay.mychain.sdk.common.VMTypeEnum;
import com.alipay.mychain.sdk.domain.account.Identity;
import com.antfinancial.mychain.baas.tool.restclient.RestClient;
import com.antfinancial.mychain.baas.tool.restclient.RestClientProperties;
import com.antfinancial.mychain.baas.tool.restclient.model.CallRestBizParam;
import com.antfinancial.mychain.baas.tool.restclient.model.Method;
import com.antfinancial.mychain.baas.tool.restclient.model.ReceiptDecoration;
import com.antfinancial.mychain.baas.tool.restclient.response.BaseResp;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;

@RequestMapping("/antblockchain")
@RestController
public class AntBlockChianController {

	
	@Autowired
    private RestClient restClient;

    @Autowired
    private RestClientProperties restClientProperties;
    
    private static Logger logger = LoggerFactory.getLogger(AntBlockChianController.class);
    
    @PostMapping("/deploy")
    public Response<BaseResp> deployContract(String code, String contractName) throws Exception {
    	String orderId = "order_" + System.currentTimeMillis();
        CallRestBizParam callRestBizParam=CallRestBizParam.builder()
                .orderId(orderId)
                .bizid(restClientProperties.getBizid())
                .account(restClientProperties.getAccount())
                .contractName(contractName)
                .contractCode(code)
                .mykmsKeyId(restClientProperties.getKmsId())
                    .method(Method.DEPLOYCONTRACTFORBIZASYNC)

                .tenantid(restClientProperties.getTenantid())
                // 确保设置的Gas参数足够大，且执行创建的账户中有足够Gas，并且账户燃料要大于参数数值
                .gas(10000000L)
                .build();
        BaseResp resp=restClient.bizChainCallWithReceipt(callRestBizParam);
        if(!resp.isSuccess()) {
            logger.error("EVM合约部署执行失败: " + resp.getCode() + ", " + resp.getData());
        }
        if("200".equals(resp.getCode())) {
            logger.info("EVM合约部署执行成功");
            logger.info(JSON.toJSONString(resp));
        } else {
            // 异步交易未成功需要根据状态码判断交易状态
            logger.error("EVM合约部署执行未成功: " + resp.getCode());
        }
        // 合约部署交易回执内容
        ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
        BigInteger gasUsed = txReceipt.getGasUsed();
        long result = txReceipt.getResult();
        logger.info("EVM合约部署交易内容: 哈希 " + txReceipt.getHash() + ", 消耗燃料 " + gasUsed + ", 结果 " + result);
        return ResponseGenerator.successResponse(resp);
    }
    
    @PostMapping("/address")
    public Response<Identity> getAddress(String name) throws UnsupportedEncodingException{
    	byte[] bytes = name.getBytes("utf-8");
    	byte[] hex = DigestUtils.sha256(bytes);
    	byte[] b = Base64.encode(hex);
    	return ResponseGenerator.successResponse(Utils.getIdentityByName(name));
    }
    
    @PostMapping("/call")
    public Response<BaseResp> callContract(String contractName, String method, String inputs, String outTypes) throws Exception {
    	String orderId = "order_" + System.currentTimeMillis();
        CallRestBizParam callRestBizParam=CallRestBizParam.builder()
                .orderId(orderId)
                .bizid(restClientProperties.getBizid())
                .account(restClientProperties.getAccount())
                .contractName(contractName)
                //合约方法签名  注意：1.中间不要带空格  2.只写参数类型,不要带参数名 如:uint256 a,uint256 b  3.参数类型填写争取  请不要填写如int  需要填写完整uint
                .methodSignature(method)
                .inputParamListStr(inputs)
                .outTypes(outTypes)//合约返回值类型
                .mykmsKeyId(restClientProperties.getKmsId())
                .method(Method.CALLCONTRACTBIZASYNC)
                .tenantid(restClientProperties.getTenantid())
                .gas(5000000L)
                .build();
        BaseResp resp=restClient.bizChainCallWithReceipt(callRestBizParam);
        if(!resp.isSuccess()) {
            logger.error("EVM合约执行失败: " + resp.getCode() + ", " + resp.getData());
        }
        if("200".equals(resp.getCode())) {
            logger.info("EVM合约执行成功");

        } else {
            // 异步交易未成功需要根据状态码判断交易状态
           logger.info("EVM合约执行未成功: " + resp.getCode());
        }
        // 合约调用交易回执内容
        ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
        BigInteger gasUsed = txReceipt.getGasUsed();
        long result = txReceipt.getResult();
        logger.info("EVM合约交易内容: 哈希 " + txReceipt.getHash() + ", 消耗燃料 " + gasUsed + ", 结果 " + result);
       return ResponseGenerator.successResponse(resp);
    }
    
    @PostMapping("/show")
    public Response<BaseResp> showOutput(String hash, String abi) throws Exception {
    	 BaseResp queryBaseResp = restClient.chainCall(hash, restClientProperties.getBizid(), "", Method.QUERYRECEIPT);
    	 logger.info(JSON.toJSONString(queryBaseResp));
         String s = queryBaseResp.getData();
         String output = s.substring(s.indexOf("output") + 9, s.indexOf("result")).replace("\"", "").replace(",", "");
         if (0 != output.length()) {
             BaseResp show = show(output, abi);
             return ResponseGenerator.successResponse(show);
         }else {
             return ResponseGenerator.successResponse(queryBaseResp);
         }
    }
    
    private BaseResp show(String output, String abi) throws Exception {
        byte[] content = Hex.encode(Base64.decode(output));
        CallRestBizParam callRestBizParam = CallRestBizParam.builder().
                bizid(restClientProperties.getBizid())
                .method(Method.PARSEOUTPUT).
                tenantid(restClientProperties.getTenantid()).
                orderId("order_" + System.currentTimeMillis()).
                vmTypeEnum(VMTypeEnum.EVM)
                .content(new String(content)).
                abi(abi).                                      //TODO 合约返回值类型需自己根据合约修改
                mykmsKeyId(restClientProperties.getKmsId()).build();    //TODO 默认为application.yaml中KmsId
        BaseResp baseResp = restClient.chainCallForBiz(callRestBizParam);
        assert (baseResp.isSuccess());
        System.out.println("show" + baseResp);
        return baseResp;
    }
    
    

}
