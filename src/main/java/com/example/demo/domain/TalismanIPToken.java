package com.example.demo.domain;

import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "符IP代币")
public class TalismanIPToken extends IPToken {

	@ApiModelProperty(value = "功效")
	protected String function;// 功效

	@ApiModelProperty(value = "文件hash")
	protected String imgHash;// 文件hash

	@ApiModelProperty(value = "付费模式")
	protected AccessPaymentPlan accessPaymentPlan;

	@ApiModelProperty(value = "是否出售")
	protected boolean isForSale;// 是否出售

	@ApiModelProperty(value = "代币的出售价格")
	protected int tokenPrice;// 代币的出售价格

	@ApiModelProperty(value = "法币的出售价格")
	protected float currencyPrice; // 法币的出售价格

	protected int totalSupply;

	protected int remianSupply;

	protected BigInteger projectId;

	protected BigInteger abstractId;

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getImgHash() {
		return imgHash;
	}

	public void setImgHash(String imgHash) {
		this.imgHash = imgHash;
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
