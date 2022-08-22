package com.example.demo.service.imp;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.contract.service.AntBlockChainService;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.service.UserService;
import com.example.demo.util.WechatUtil;

@Service
public class AntChainUserServiceImp implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(AntChainUserServiceImp.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WechatUtil wechatUtil;
	
	@Autowired
	private AntBlockChainService antBlockChainService;

	@Override
	public User wxlogin(String code, String rawData, String signature, String encrypteData, String iv) throws Exception {
		JSONObject sessionAndOpenId = wechatUtil.getSessionKeyOrOpenId(code);
		String openId = sessionAndOpenId.getString("openid");
		User user = userMapper.selectByOpenId(openId);
		if (user == null) {
			JSONObject userInfo = wechatUtil.getUserInfo(encrypteData, sessionAndOpenId.getString("session_key"), iv);
			logger.info(userInfo.toJSONString());
			user = new User();
			user.setOpenId(openId);
			user.setAvatarUrl(userInfo.getString("avatarUrl"));
			user.setNickName(userInfo.getString("nickName"));
			String account = "tips_" + System.currentTimeMillis();
			String kmsId = UUID.randomUUID().toString();
			antBlockChainService.creatAccount(account, kmsId);
			String address = antBlockChainService.address(account);
			user.setBlockChainName(account);
			user.setBlockChainAddress(address);
			user.setPrivateKey(kmsId);
			userMapper.insert(user);
		}
		return user;
	}

	@Override
	public User selectByOpenId(String openId) {
		User user = userMapper.selectByOpenId(openId);
		if (user == null) {
			throw new BaseException(ExceptionEnum.UNKOWN_USER);
		}
		return user;
	}

	@Override
	public User selectByBlockChainAddress(String blockChainAddress) {
		User condition = new User();
		condition.setBlockChainAddress(blockChainAddress);
		List<User> users = userMapper.select(condition);
		if (users.size() != 1) {
			logger.error(String.format("UserFoundError:%s", blockChainAddress));
			throw new BaseException(ExceptionEnum.UNKOWN_USER);
		}
		return users.get(0);
	}

}
