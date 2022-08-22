package com.example.demo.service.imp;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.service.UserService;
import com.example.demo.util.WechatUtil;

public class UserServiceImp implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WechatUtil wechatUtil;

	@Override
	public User wxlogin(String code, String rawData, String signature, String encrypteData, String iv) {
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
			generatePrivateKey(user);
			userMapper.insert(user);
		}
		return user;
	}

	private void generatePrivateKey(User user) {
		try {
			ECKeyPair ecKeyPair = Keys.createEcKeyPair();
			BigInteger privateKeyInDec = ecKeyPair.getPrivateKey();
			String privateKey = privateKeyInDec.toString(16);
			WalletFile aWallet = Wallet.createLight(UUID.randomUUID().toString(), ecKeyPair);
			String address = aWallet.getAddress();
			if (address.startsWith("0x")) {
				address = address.substring(2).toLowerCase();
			} else {
				address = address.toLowerCase();
			}
			address = "0x" + address;
			logger.info("地址：" + address);
			logger.info("秘钥：" + privateKey);
			user.setPrivateKey(privateKey);
			user.setBlockChainAddress(address);
		} catch (Exception e) {
			logger.error(e.getCause().toString());
			throw new BaseException(ExceptionEnum.USER_CREATE_ERROR);
		}
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
