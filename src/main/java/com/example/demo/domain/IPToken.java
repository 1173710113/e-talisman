package com.example.demo.domain;

import java.math.BigInteger;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author PhilPhobia
 *
 */
@ApiModel(description = "IP代币")
public class IPToken {

	@ApiModelProperty(value = "IP所在合约的tokenID")
	protected BigInteger tokenID;// IP所在合约的tokenID
	
	@ApiModelProperty(value = "IP所在合约地址")
	protected String contractAddress;// IP所在合约地址
	
	@ApiModelProperty(value = "IP的名称")
	protected String name;// IP的名称
	
	@ApiModelProperty(value = "// IP的权属拥有者与其占有的权属数量")
	protected Map<User, Integer> equity;// IP的权属拥有者与其占有的权属数量
	
	@ApiModelProperty(value = "// IP的创建者")
	protected User creator; // IP的创建者
	
	@ApiModelProperty(value = "IP文件hash")
	protected String fileHash;
	
	@ApiModelProperty(value = "// IP类型")
	protected String Type;// IP类型

	public BigInteger getTokenID() {
		return tokenID;
	}

	public void setTokenID(BigInteger tokenID) {
		this.tokenID = tokenID;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<User, Integer> getEquity() {
		return equity;
	}

	public void setEquity(Map<User, Integer> equity) {
		this.equity = equity;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

}
