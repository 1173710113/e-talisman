package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.User;
import com.example.demo.mapper.ArtMapper;
import com.example.demo.service.AntChainArtService;
import com.example.demo.util.FileUtil;

import io.ipfs.api.MerkleNode;


@Service
public class AntChainArtServiceImp implements AntChainArtService{
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	private ArtMapper artMapper;
	
	
	//private static Logger logger = LoggerFactory.getLogger(ArtServiceImp.class);
	
	@Override
	public MerkleNode saveArtInfo(List<MultipartFile> files, AccessPaymentPlan accessPaymentPlan, Integer totalSupply) {
		List<MerkleNode> nodes = fileUtil.upload(files);
		List<String> fileHashList = new ArrayList<String>();
		for(MerkleNode node : nodes) {
			fileHashList.add(node.hash.toBase58());
		}
		JSONObject object = new JSONObject();
		object.put("accessPaymentPlan", accessPaymentPlan);
		object.put("files", fileHashList);
		object.put("totalSupply", totalSupply);
		MerkleNode jsonNode = fileUtil.upload("append.json", object.toJSONString().getBytes());
		return jsonNode;
	}


	@Override
	public List<ArtIPToken> selectIArtByOwner(String openId) {
		ArtIPToken condition = new ArtIPToken();
		User creator = new User();
		creator.setOpenId(openId);
		condition.setCreator(creator);
		return artMapper.select(condition);
	}


	@Override
	public List<ArtIPToken> selectAllArt() {
		ArtIPToken condition = new ArtIPToken();
		return artMapper.select(condition);
	}


	@Override
	public ArtIPToken selectArtById(BigInteger id) {
		return artMapper.selectById(id);
	}



}
