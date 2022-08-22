package com.example.demo.domain.factory;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.IPToken;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.util.FileUtil;



@Component
public class IPFactory {

	private static FileUtil fileUtil;
	
	private static Logger logger = LoggerFactory.getLogger(IPFactory.class);
	
	@Autowired
	public void setFileUtil(FileUtil fileUtil) {
		IPFactory.fileUtil = fileUtil;
	}
	
	public static TalismanIPToken getTalismanIPToken(IPToken ipToken) {
		TalismanIPToken talismanIPToken = new TalismanIPToken();
		generalConvert(ipToken, talismanIPToken);
		byte[] bytes = fileUtil.get(ipToken.getFileHash());
		logger.info(new String(bytes));
		JSONObject object = JSON.parseObject(new String(bytes));
		talismanIPToken.setFunction(object.getString("function"));
		talismanIPToken.setImgHash(object.getString("img"));
		talismanIPToken.setAccessPaymentPlan(JSON.parseObject(object.getString("accessPaymentPlan"),AccessPaymentPlan.class));
		talismanIPToken.setTotalSupply(object.getIntValue("totalSupply"));
		return talismanIPToken;
	}
	
	public static List<TalismanIPToken> getTalismanIPToken(List<IPToken> ipTokens){
		List<TalismanIPToken> talismanIPTokens = new ArrayList<TalismanIPToken>();
		for(IPToken ipToken : ipTokens) {
			talismanIPTokens.add(getTalismanIPToken(ipToken));
		}
		return talismanIPTokens;
	}
	
	private static void generalConvert(IPToken from, IPToken to) {
		to.setTokenID(from.getTokenID());
		to.setCreator(from.getCreator());
		to.setContractAddress(from.getContractAddress());
		to.setEquity(from.getEquity());
		to.setFileHash(from.getFileHash());
	}
	
	public static ArtIPToken getArtIPToken(IPToken ipToken) {
		ArtIPToken artIPToken = new ArtIPToken();
		generalConvert(ipToken, artIPToken);
		byte[] bytes = fileUtil.get(ipToken.getFileHash());
		logger.info(new String(bytes));
		JSONObject object = JSON.parseObject(new String(bytes));
		JSONArray jsonArray = object.getJSONArray("files");
		List<String> array = JSONObject.parseArray(jsonArray.toJSONString(), String.class);
		artIPToken.setFileHashs(array);
		artIPToken.setAccessPaymentPlan(JSON.parseObject(object.getString("accessPaymentPlan"),AccessPaymentPlan.class));
		artIPToken.setTotalSupply(object.getIntValue("totalSupply"));
		return artIPToken;
	}
	
	public static List<ArtIPToken> getArtIpToken(List<IPToken> ipTokens){
		List<ArtIPToken> artIPTokens = new ArrayList<ArtIPToken>();
		for(IPToken ipToken : ipTokens) {
			artIPTokens.add(getArtIPToken(ipToken));
		}
		return artIPTokens;
				
	}
}
