package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "eth.config")
public class ETHConfig {

	private String url;
	private String abstractAddress;
	private String workAddress;
	private String projectAddress;
	private String committeeAddress;
	private String commodifyRightAddress;
	private String commodityAddress;
	// contract address

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAbstractAddress() {
		return abstractAddress;
	}

	public void setAbstractAddress(String abstractAddress) {
		this.abstractAddress = abstractAddress;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getProjectAddress() {
		return projectAddress;
	}

	public void setProjectAddress(String projectAddress) {
		this.projectAddress = projectAddress;
	}

	public String getCommitteeAddress() {
		return committeeAddress;
	}

	public void setCommitteeAddress(String committeeAddress) {
		this.committeeAddress = committeeAddress;
	}

	public String getCommodifyRightAddress() {
		return commodifyRightAddress;
	}

	public void setCommodifyRightAddress(String commodifyRightAddress) {
		this.commodifyRightAddress = commodifyRightAddress;
	}

	public String getCommodityAddress() {
		return commodityAddress;
	}

	public void setCommodityAddress(String commodityAddress) {
		this.commodityAddress = commodityAddress;
	}

}
