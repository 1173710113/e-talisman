package com.example.demo.mapper;

import java.math.BigInteger;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkProjectMapper {

	public void insert(BigInteger projectId, BigInteger workId);
	
	public BigInteger selectProjectId(BigInteger workId);
}
