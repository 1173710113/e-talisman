package com.example.demo.util;

import java.math.BigInteger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import com.example.demo.config.ETHConfig;
import com.example.demo.contract.Abstract;
import com.example.demo.contract.Committee;
import com.example.demo.contract.CommodifyRight;
import com.example.demo.contract.Commodity;
import com.example.demo.contract.Project;
import com.example.demo.contract.Work;

@Component
public class ContractUtil {

	@Autowired
	private ETHConfig ethConfig;

	private Web3j web3j;

	private ContractGasProvider provider;

	@PostConstruct
	public void postConstruct() {
		web3j = Web3j.build(new HttpService(ethConfig.getUrl()));
		provider = new StaticGasProvider(BigInteger.valueOf(20000000000L), BigInteger.valueOf(6721975L));
	}

	public Web3j getWeb3j() {
		return web3j;
	}

	public Abstract getAbstract(Credentials credentials) {
		Abstract contract = Abstract.load(ethConfig.getAbstractAddress(), web3j, credentials, provider);
		return contract;
	}

	public Work getWork(Credentials credentials) {
		return Work.load(ethConfig.getWorkAddress(), web3j, credentials, provider);
	}

	public Project getProject(Credentials credentials) {
		Project contract = Project.load(ethConfig.getProjectAddress(), web3j, credentials, provider);
		return contract;
	}

	public Committee getCommittee(Credentials credentials) {
		Committee contract = Committee.load(ethConfig.getCommitteeAddress(), web3j, credentials, provider);
		return contract;
	}

	public CommodifyRight getCommodifyRight(Credentials credentials) {
		return CommodifyRight.load(ethConfig.getCommodifyRightAddress(), web3j, credentials, provider);
	}

	public Commodity getCommodity(Credentials credentials) {
		return Commodity.load(ethConfig.getCommodityAddress(), web3j, credentials, provider);
	}

	public Credentials getCredentials(String privateKey) {
		return Credentials.create(privateKey);
	}
}
