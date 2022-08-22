package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.factory.IPFactory;
import com.example.demo.service.ArtService;
import com.example.demo.util.FileUtil;

import io.ipfs.api.MerkleNode;


public class ArtServiceImp implements ArtService{
	
	@Autowired
	private FileUtil fileUtil;
	
	@Autowired
	ContractService contractService;
	
	//private static Logger logger = LoggerFactory.getLogger(ArtServiceImp.class);
	
	@Override
	public MerkleNode saveArtInfo(List<MultipartFile> files, AccessPaymentPlan accessPaymentPlan) {
		List<MerkleNode> nodes = fileUtil.upload(files);
		List<String> fileHashList = new ArrayList<String>();
		for(MerkleNode node : nodes) {
			fileHashList.add(node.hash.toBase58());
		}
		JSONObject object = new JSONObject();
		object.put("accessPaymentPlan", accessPaymentPlan);
		object.put("files", fileHashList);
		MerkleNode jsonNode = fileUtil.upload("append.json", object.toJSONString().getBytes());
		return jsonNode;
	}

	@Override
	public void sellArt(int ArtTokenID, String address, Map<String, Integer> paymentPlan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buyArtByToken(int ArtTokenID, String address, String paymentPlan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArtIPToken> selectArtIPForSale() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ArtIPToken> selectIPTokenByOwner(Credentials credentials) {
		return IPFactory.getArtIpToken(contractService.selectByOwner(credentials));
	}
	
	@Override
	public List<ArtIPToken> selectAllIPToken(Credentials credentials){
		return IPFactory.getArtIpToken(contractService.selectAll(credentials));
	}
	
	@Override
	public ArtIPToken selectArtIPTokenById(Credentials credentials, BigInteger id) {
		return IPFactory.getArtIPToken(contractService.selectById(credentials, id));
	}
	

	@Override
	public List<ETalisman> selectETalismanToken(int artTokenID, String address) {
		// TODO Auto-generated method stub
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
