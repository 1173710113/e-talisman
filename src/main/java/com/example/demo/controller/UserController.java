package com.example.demo.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.User;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.msghandler.Response;
import com.example.demo.msghandler.ResponseGenerator;
import com.example.demo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="用户接口")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	

	/**
	 * 
	 * @param code
	 * @param rawData
	 * @param signature
	 * @param encrypteData
	 * @param iv
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "微信登入")
	@PostMapping("/wxlogin")
	public Response<User> wxlogin(String code, String rawData, String signature, String encrypteData, String iv) throws Exception {
		User user = userService.wxlogin(code, rawData, signature, encrypteData, iv);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(new UsernamePasswordToken(user.getOpenId(), ""));
		} catch (UnknownAccountException e) {
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.UNKOWN_USER);
		}
		return ResponseGenerator.successResponse(user);

	}

	
}
