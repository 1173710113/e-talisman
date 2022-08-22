package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.abi.datatypes.Address;
import org.web3j.crypto.Credentials;

import com.alibaba.fastjson.JSON;
import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.User;
import com.example.demo.domain.factory.IPFactory;
import com.example.demo.mapper.ETalismanMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.service.ETalismanService;

public class ETalismanServiceImp implements ETalismanService{
	
	private static Logger logger = LoggerFactory.getLogger(ETalismanServiceImp.class);
	
	@Autowired
	private ETalismanMapper etalismanMapper;
	
	@Autowired
	private ContractService contractService;
	
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public BigInteger newETalisman(String openId, Date expireTime, BigInteger talismanId, BigInteger artId) {
		ETalisman etalisman = new ETalisman();
		User owner = new User();
		owner.setOpenId(openId);
		etalisman.setOwner(owner);
		etalisman.setExpireTime(expireTime);
		etalisman.setTalismanId(talismanId);
		etalisman.setArtId(artId);
		etalismanMapper.insert(etalisman);
		return etalisman.getEtalismanId();
	}

	@Override
	public void buyETalisman(int eTalismanTokenID, Address address, String paymentPlan) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<ETalisman> selectETalismanByOwner(Address address) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ETalisman> selectByUser(Credentials credentials) {
		ETalisman condition = new ETalisman();
		User owner = new User();
		owner.setBlockChainAddress(credentials.getAddress());
		condition.setOwner(owner);
		List<ETalisman> etalismans = etalismanMapper.select(condition);
		logger.info(JSON.toJSONString(etalismans));
		for(ETalisman etalisman : etalismans) {
			etalisman.setArt(IPFactory.getArtIPToken(contractService.selectById(credentials, etalisman.getArtId())));
			etalisman.setTalisman(IPFactory.getTalismanIPToken(contractService.selectById(credentials, etalisman.getTalismanId())));
		}
		return etalismans;
	}

	@Override
	public ETalisman selectById(Credentials credentials, BigInteger id) {
		ETalisman condition = new ETalisman();
		User owner = new User();
		owner.setBlockChainAddress(credentials.getAddress());
		condition.setOwner(owner);
		condition.setEtalismanId(id);
		List<ETalisman> etalismans = etalismanMapper.select(condition);
		if(etalismans.size() != 1) {
			throw new BaseException(ExceptionEnum.INFO_SEARCH_FAILED);
		}
		ETalisman etalisman = etalismans.get(0);
		etalisman.setArt(IPFactory.getArtIPToken(contractService.selectById(credentials, etalisman.getArtId())));
		etalisman.setTalisman(IPFactory.getTalismanIPToken(contractService.selectById(credentials, etalisman.getTalismanId())));
		return etalisman;
	}

	@Override
	public void transferETalisman(String fromOpenId,String toOpenId, BigInteger etalismanId, String message) {
		ETalisman condition = new ETalisman();
		User owner = new User();
		owner.setOpenId(fromOpenId);
		condition.setOwner(owner);
		condition.setEtalismanId(etalismanId);
		List<ETalisman> eTalismans = etalismanMapper.select(condition);
		if(eTalismans.size() != 1) {
			throw new BaseException(ExceptionEnum.INFO_SEARCH_FAILED);
		}
		ETalisman eTalisman = eTalismans.get(0);
		owner.setOpenId(toOpenId);
		eTalisman.setOwner(owner);
		etalismanMapper.updateById(eTalisman);
	}

}
