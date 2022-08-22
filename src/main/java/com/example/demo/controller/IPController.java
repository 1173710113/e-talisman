package com.example.demo.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;

import com.example.demo.contract.service.ContractService;
import com.example.demo.domain.IPToken;
import com.example.demo.domain.User;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.util.ContractUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

public class IPController {

	@Autowired
	private ContractService contractService;

	@Autowired
	private ContractUtil contractUtil;

	/**
	 * 
	 * @return
	 */
	@ApiOperation(value = "查找用户有的所有IP")
	@PostMapping("/query/user")
	public Response<List<IPToken>> selectByOwner() {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Credentials credentials = contractUtil.getCredentials(user.getPrivateKey());
		return ResponseGenerator.successResponse(contractService.selectByOwner(credentials));
	}
}
