package com.example.demo.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.ArtIPToken;


@Mapper
public interface ArtMapper {

	public void insert(ArtIPToken artIPToken);
	
	public void update(ArtIPToken condition);
	
	public ArtIPToken selectById(BigInteger id);
	
	public List<ArtIPToken> select(ArtIPToken condition);

}
