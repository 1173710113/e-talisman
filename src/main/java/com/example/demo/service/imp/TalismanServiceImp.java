package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.factory.IPFactory;
import com.example.demo.service.TalismanService;
import com.example.demo.util.FileUtil;

import io.ipfs.api.MerkleNode;


public class TalismanServiceImp implements TalismanService {

	private final static Logger logger = LoggerFactory.getLogger(TalismanServiceImp.class);

	@Autowired
	FileUtil fileUtil;
	
	@Autowired
	ContractService contractService;
	
	@Override
	public MerkleNode saveTalismanInfo(MultipartFile file, String function, AccessPaymentPlan accessPaymentPlan, Integer totalSupply) {
		MerkleNode fileNode = fileUtil.upload(file);
		//上传json
		JSONObject object = new JSONObject();
		object.put("img", fileNode.hash.toBase58());
		object.put("function", function);
		object.put("accessPaymentPlan",accessPaymentPlan);
		object.put("totalSupply", totalSupply);
		MerkleNode jsonNode = fileUtil.upload("append.json", object.toJSONString().getBytes());
		return jsonNode;
	}

	@Override
	public List<TalismanIPToken> selectTalismanByOwner(Credentials credentials) {
		return IPFactory.getTalismanIPToken(contractService.selectByOwner(credentials));
	}
	
	@Override
	public List<TalismanIPToken> selectAllTalisman(Credentials credentials){
		return IPFactory.getTalismanIPToken(contractService.selectAll(credentials));
	}
	
	@Override
	public TalismanIPToken selectTalismanById(Credentials credentials, BigInteger id) {
		return IPFactory.getTalismanIPToken(contractService.selectById(credentials, id));
	}

	@Override
	public List<ETalisman> selectETalismanToken(int talismanTokenID, String address) {
		// TODO 
		return null;
	}

	@Override
	public int tokenProfit(int artToken, String address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float curencyProfit(int artToken, String address) {
		// TODO Auto-generated method stub
		return 0;
	}



}
