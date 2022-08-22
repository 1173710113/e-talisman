package com.example.demo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户")
public class User {

	@ApiModelProperty(value = "昵称")
	private String nickName;
	
	@ApiModelProperty(value = "openId")
	private String openId;
	
	@ApiModelProperty(value = "头像地址")
	private String avatarUrl;
	
	@ApiModelProperty(value = "要删掉的")
	private String privateKey;
	
	@ApiModelProperty(value = "区块链地址")
	private String blockChainAddress;
	
	private String blockChainName;

	public User() {
		super();
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getBlockChainAddress() {
		return blockChainAddress;
	}

	public void setBlockChainAddress(String blockChainAddress) {
		this.blockChainAddress = blockChainAddress;
	}
	
	
	

	public String getBlockChainName() {
		return blockChainName;
	}

	public void setBlockChainName(String blockChainName) {
		this.blockChainName = blockChainName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((openId == null) ? 0 : openId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (openId == null) {
			if (other.openId != null)
				return false;
		} else if (!openId.equals(other.openId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [nickName=" + nickName + ", openId=" + openId + ", avatarUrl=" + avatarUrl + ", privateKey="
				+ privateKey + ", blockChainAddress=" + blockChainAddress + ", blockChainName=" + blockChainName + "]";
	}

	

}
