package com.example.demo.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.TalismanIPToken;


@Mapper
public interface TalismanMapper {

	public void insert(TalismanIPToken talismanIPToken);
	
	public void update(TalismanIPToken condition);
	
	public TalismanIPToken selectById(BigInteger id);
	
	public List<TalismanIPToken> select(TalismanIPToken condition);

}
