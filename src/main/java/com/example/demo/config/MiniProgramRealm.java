package com.example.demo.config;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;

public class MiniProgramRealm extends AuthorizingRealm{

	@Resource
	private UserService userService;
	
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName = (String)token.getPrincipal();
		User user = userService.selectByOpenId(userName);
		if(user == null) {
			throw new UnknownAccountException(); 
		}
		return new SimpleAuthenticationInfo(user,"", getName());
	}

}
