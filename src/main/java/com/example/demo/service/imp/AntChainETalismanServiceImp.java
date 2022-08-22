package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ETalisman;
import com.example.demo.domain.User;
import com.example.demo.mapper.ArtMapper;
import com.example.demo.mapper.ETalismanMapper;
import com.example.demo.mapper.TalismanMapper;
import com.example.demo.service.AntChainETalismanService;

@Service
public class AntChainETalismanServiceImp implements AntChainETalismanService{
	
	
	@Autowired
	private ETalismanMapper etalismanMapper;
	
	@Autowired
	private TalismanMapper talismanMapper;
	
	@Autowired
	private ArtMapper artMapper;

	@Override
	public List<ETalisman> selectByUser(String openId) {
		ETalisman condition = new ETalisman();
		User owner = new User();
		owner.setOpenId(openId);
		condition.setOwner(owner);
		List<ETalisman> etalismans = etalismanMapper.select(condition);
		for(ETalisman etalisman : etalismans) {
			etalisman.setTalisman(talismanMapper.selectById(etalisman.getTalismanId()));
			etalisman.setArt(artMapper.selectById(etalisman.getArtId()));
		}
		return etalismans;
	}

	@Override
	public ETalisman selectById(BigInteger id) {
		ETalisman etalisman = etalismanMapper.selectById(id);
		etalisman.setTalisman(talismanMapper.selectById(etalisman.getTalismanId()));
		etalisman.setArt(artMapper.selectById(etalisman.getArtId()));
		return etalisman;
	}

}
