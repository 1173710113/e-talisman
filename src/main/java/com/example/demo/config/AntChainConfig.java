package com.example.demo.config;

import java.math.BigInteger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "antchain.config")
public class AntChainConfig {

	private String serviceContractName;
	private String projectContractName;
	private String commodityContractName;
	private BigInteger talismanTipVotingPower;
	private BigInteger talismanTianshifuVotingPower;
	private BigInteger talismanCreatorVotingPower;
	private BigInteger artTipVotingPower;
	private BigInteger artCreatorVotingPower;
	public String getServiceContractName() {
		return serviceContractName;
	}
	public void setServiceContractName(String serviceContractName) {
		this.serviceContractName = serviceContractName;
	}
	public String getProjectContractName() {
		return projectContractName;
	}
	public void setProjectContractName(String projectContractName) {
		this.projectContractName = projectContractName;
	}
	public String getCommodityContractName() {
		return commodityContractName;
	}
	public void setCommodityContractName(String commodityContractName) {
		this.commodityContractName = commodityContractName;
	}
	public BigInteger getTalismanTipVotingPower() {
		return talismanTipVotingPower;
	}
	public void setTalismanTipVotingPower(BigInteger talismanTipVotingPower) {
		this.talismanTipVotingPower = talismanTipVotingPower;
	}
	public BigInteger getTalismanTianshifuVotingPower() {
		return talismanTianshifuVotingPower;
	}
	public void setTalismanTianshifuVotingPower(BigInteger talismanTianshifuVotingPower) {
		this.talismanTianshifuVotingPower = talismanTianshifuVotingPower;
	}
	public BigInteger getTalismanCreatorVotingPower() {
		return talismanCreatorVotingPower;
	}
	public void setTalismanCreatorVotingPower(BigInteger talismanCreatorVotingPower) {
		this.talismanCreatorVotingPower = talismanCreatorVotingPower;
	}
	public BigInteger getArtTipVotingPower() {
		return artTipVotingPower;
	}
	public void setArtTipVotingPower(BigInteger artTipVotingPower) {
		this.artTipVotingPower = artTipVotingPower;
	}
	public BigInteger getArtCreatorVotingPower() {
		return artCreatorVotingPower;
	}
	public void setArtCreatorVotingPower(BigInteger artCreatorVotingPower) {
		this.artCreatorVotingPower = artCreatorVotingPower;
	}

	

}
