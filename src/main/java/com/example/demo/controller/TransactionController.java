package com.example.demo.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Transaction;
import com.example.demo.domain.User;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="交易接口")
@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	
	@Autowired
	private TransactionService transactionService;

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "查找用户所有交易")
	@PostMapping("/query/user")
	public Response<List<Transaction>> selectUserTransaction(){
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		return ResponseGenerator.successResponse(transactionService.selectUserTransaction(user.getOpenId()));
	}
}
