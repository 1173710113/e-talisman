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
                // ???????????????Gas??????????????????????????????????????????????????????Gas??????????????????????????????????????????
                .gas(10000000L)
                .build();
        BaseResp resp=restClient.bizChainCallWithReceipt(callRestBizParam);
        if(!resp.isSuccess()) {
            logger.error("EVM????????????????????????: " + resp.getCode() + ", " + resp.getData());
        }
        if("200".equals(resp.getCode())) {
            logger.info("EVM????????????????????????");
            logger.info(JSON.toJSONString(resp));
        } else {
            // ????????????????????????????????????????????????????????????
            logger.error("EVM???????????????????????????: " + resp.getCode());
        }
        // ??????????????????????????????
        ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
        BigInteger gasUsed = txReceipt.getGasUsed();
        long result = txReceipt.getResult();
        logger.info("EVM????????????????????????: ?????? " + txReceipt.getHash() + ", ???????????? " + gasUsed + ", ?????? " + result);
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
                //??????????????????  ?????????1.?????????????????????  2.??????????????????,?????????????????? ???:uint256 a,uint256 b  3.????????????????????????  ??????????????????int  ??????????????????uint
                .methodSignature(method)
                .inputParamListStr(inputs)
                .outTypes(outTypes)//?????????????????????
                .mykmsKeyId(restClientProperties.getKmsId())
                .method(Method.CALLCONTRACTBIZASYNC)
                .tenantid(restClientProperties.getTenantid())
                .gas(5000000L)
                .build();
        BaseResp resp=restClient.bizChainCallWithReceipt(callRestBizParam);
        if(!resp.isSuccess()) {
            logger.error("EVM??????????????????: " + resp.getCode() + ", " + resp.getData());
        }
        if("200".equals(resp.getCode())) {
            logger.info("EVM??????????????????");

        } else {
            // ????????????????????????????????????????????????????????????
           logger.info("EVM?????????????????????: " + resp.getCode());
        }
        // ??????????????????????????????
        ReceiptDecoration txReceipt = JSON.parseObject(resp.getData(), ReceiptDecoration.class);
        BigInteger gasUsed = txReceipt.getGasUsed();
        long result = txReceipt.getResult();
        logger.info("EVM??????????????????: ?????? " + txReceipt.getHash() + ", ???????????? " + gasUsed + ", ?????? " + result);
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
                abi(abi).                                      //TODO ????????????????????????????????????????????????
                mykmsKeyId(restClientProperties.getKmsId()).build();    //TODO ?????????application.yaml???KmsId
        BaseResp baseResp = restClient.chainCallForBiz(callRestBizParam);
        assert (baseResp.isSuccess());
        System.out.println("show" + baseResp);
        return baseResp;
    }
    
    

}
