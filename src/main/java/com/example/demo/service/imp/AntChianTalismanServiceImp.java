package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.User;
import com.example.demo.mapper.TalismanMapper;
import com.example.demo.service.AntChainTalismanService;
import com.example.demo.util.FileUtil;

import io.ipfs.api.MerkleNode;


@Service
public class AntChianTalismanServiceImp implements AntChainTalismanService {

	private final static Logger logger = LoggerFactory.getLogger(AntChianTalismanServiceImp.class);

	@Autowired
	FileUtil fileUtil;
	
	@Autowired
	private TalismanMapper talismanMapper;
	
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
	public List<TalismanIPToken> selectAllTalisman(){
		TalismanIPToken condition = new TalismanIPToken();
		return talismanMapper.select(condition);
	}
	
	@Override
	public TalismanIPToken selectTalismanById(BigInteger id) {
		return talismanMapper.selectById(id);
	}

	@Override
	public List<TalismanIPToken> selectTalismanByOwner(String openId) {
		TalismanIPToken condition = new TalismanIPToken();
		User creator = new User();
		creator.setOpenId(openId);
		condition.setCreator(creator);
		return talismanMapper.select(condition);
	}



}
