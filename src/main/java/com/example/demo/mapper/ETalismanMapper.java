package com.example.demo.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.ETalisman;

@Mapper
public interface ETalismanMapper {

	public void insert(ETalisman etalisman);
	
	public void updateById(ETalisman etalisman);
	
	public List<ETalisman> select(ETalisman condition);
	
	public ETalisman selectById(BigInteger id);
	
	
}
