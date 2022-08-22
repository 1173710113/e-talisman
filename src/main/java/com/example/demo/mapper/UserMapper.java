package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.User;

@Mapper
public interface UserMapper {

	/**
	 * 插入用户表，openId必填
	 * @param user
	 */
	public void insert(User user);
	
	
	/**
	 * 通过openId查找用户
	 * @param openId
	 * @return
	 */
	public User selectByOpenId(String openId);
	
	/**
	 * 
	 * @param condition
	 * @return
	 */
	public List<User> select(User condition);
}
