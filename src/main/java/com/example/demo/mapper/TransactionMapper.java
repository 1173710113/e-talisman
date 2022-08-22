package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Transaction;

@Mapper
public interface TransactionMapper {
	
	public List<Transaction> select(Transaction condition);

	public void insert(Transaction transaction);
	
	public void update(Transaction transaction,Transaction condition);
	
	public void updateById(Transaction transaction);
}
