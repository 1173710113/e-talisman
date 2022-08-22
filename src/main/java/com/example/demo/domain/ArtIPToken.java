package com.example.demo.domain;

import java.math.BigInteger;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "模板Token")
public class ArtIPToken extends IPToken {

	@ApiModelProperty(value = "文件hash列表")
	protected List<String> fileHashs;

	@ApiModelProperty(value = "付费模式")
	protected AccessPaymentPlan accessPaymentPlan;

	
	@ApiModelProperty(value = "是否出售")
	protected boolean isForSale;// 是否出售
	
	@ApiModelProperty(value = "代币的出售价格")
	protected int tokenPrice;// 代币的出售价格
	
	@ApiModelProperty(value = "法币的出售价格")
	protected float currencyPrice;// 法币的出售价格
	
	protected int totalSupply;

	protected int remianSupply;

	protected BigInteger projectId;

	protected BigInteger abstractId;

	public List<String> getFileHashs() {
		return fileHashs;
	}

	public void setFileHashs(List<String> fileHashs) {
		this.fileHashs = fileHashs;
	}

	public AccessPaymentPlan getAccessPaymentPlan() {
		return accessPaymentPlan;
	}

	public void setAccessPaymentPlan(AccessPaymentPlan accessPaymentPlan) {
		this.accessPaymentPlan = accessPaymentPlan;
	}

	public boolean isForSale() {
		return isForSale;
	}

	public void setForSale(boolean isForSale) {
		this.isForSale = isForSale;
	}

	public int getTokenPrice() {
		return tokenPrice;
	}

	public void setTokenPrice(int tokenPrice) {
		this.tokenPrice = tokenPrice;
	}

	public float getCurrencyPrice() {
		return currencyPrice;
	}

	public void setCurrencyPrice(float currencyPrice) {
		this.currencyPrice = currencyPrice;
	}

	public int getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(int totalSupply) {
		this.totalSupply = totalSupply;
	}

	public int getRemianSupply() {
		return remianSupply;
	}

	public void setRemianSupply(int remianSupply) {
		this.remianSupply = remianSupply;
	}

	public BigInteger getProjectId() {
		return projectId;
	}

	public void setProjectId(BigInteger projectId) {
		this.projectId = projectId;
	}

	public BigInteger getAbstractId() {
		return abstractId;
	}

	public void setAbstractId(BigInteger abstractId) {
		this.abstractId = abstractId;
	}

	
	

}
