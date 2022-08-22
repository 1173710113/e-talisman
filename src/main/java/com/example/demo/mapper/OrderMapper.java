package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.Order;

@Mapper
public interface OrderMapper {

	public void insert(Order order);

	public Order selectById(String id);

	public void UpdateById(Order order);
}
