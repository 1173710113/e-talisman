package com.example.demo.contract.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import com.alibaba.fastjson.JSON;
import com.example.demo.config.ETHConfig;
import com.example.demo.contract.Committee;
import com.example.demo.contract.CommodifyRight;
import com.example.demo.contract.Commodity;
import com.example.demo.contract.Project;
import com.example.demo.contract.Work;
import com.example.demo.domain.IPToken;
import com.example.demo.domain.User;
import com.example.demo.event.BuyCommodityEvent;
import com.example.demo.event.TokenUploadEvent;
import com.example.demo.mapper.WorkProjectMapper;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.service.UserService;
import com.example.demo.util.ContractUtil;

@Service
public class ContractService {

	@Autowired
	private ETHConfig ethConfig;

	@Autowired
	private ContractUtil contractUtil;

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private WorkProjectMapper workProjectMapper;

	@Autowired
	private UserService userService;

	private static Logger logger = LoggerFactory.getLogger(ContractService.class);

	public void uploadIPToken(String fileHash, Credentials credentials, TokenUploadEvent tokenUploadEvent) {
		Work workContract = contractUtil.getWork(credentials);
		Committee committeeContract = contractUtil.getCommittee(credentials);
		TransactionReceipt tempTransactionReceipt = null;
		try {
			tempTransactionReceipt = workContract.release(BigInteger.valueOf(0L), fileHash, BigInteger.valueOf(100L),
					BigInteger.valueOf(100L), ethConfig.getCommitteeAddress(), ethConfig.getAbstractAddress()).send();
		} catch (Exception e) {
			tokenUploadEvent.getTransaction().setFlag(false);
			eventPublisher.publishEvent(tokenUploadEvent);
			logger.error(e.getMessage());
			e.printStackTrace();
			throw new BaseException(ExceptionEnum.TOKEN_CREATE_FAILED);
		}
		TransactionReceipt transactionReceipt = tempTransactionReceipt;
		workContract.ipTokenEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
				.subscribe(event -> {
					if (event.log.getTransactionHash().equals(transactionReceipt.getTransactionHash())) {
						tokenUploadEvent.getTransaction().setFlag(true);
						tokenUploadEvent.setResponse(event);
						eventPublisher.publishEvent(tokenUploadEvent);
						BigInteger committeeId = committeeContract
								.searchCommitteeIdByWorkId(ethConfig.getWorkAddress(), event._tokenId).send();
						releaseProject(credentials, event._tokenId, ethConfig.getWorkAddress());
						propasal(credentials, ethConfig.getWorkAddress(), event._tokenId,
								ethConfig.getCommitteeAddress(), committeeId);

					}
				});

	}

	private void releaseProject(Credentials credentials, BigInteger workId, String workContractAddress) {
		Project projectContract = contractUtil.getProject(credentials);
		TransactionReceipt tempTransactionReceipt = null;
		try {
			tempTransactionReceipt = projectContract.release().send();
		} catch (Exception e) {
			throw new BaseException(ExceptionEnum.PROJECT_CREATE_FAILED);
		}
		TransactionReceipt transactionReceipt = tempTransactionReceipt;
		projectContract
				.afterProjecctReleaseEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
				.subscribe(event -> {
					if (event.log.getTransactionHash().equals(transactionReceipt.getTransactionHash())) {
						BigInteger projectId = event._id;
						logger.info("ProjectId:" + projectId);
						addWork(credentials, projectId, workId, workContractAddress);
						workProjectMapper.insert(projectId, workId);
					}
				});
	}

	private void addWork(Credentials credentials, BigInteger projectId, BigInteger workId, String workContractAddress) {
		Project projectContract = contractUtil.getProject(credentials);
		try {
			projectContract.addWork(projectId, workId, workContractAddress).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
		}

	}

	public void propasal(Credentials credentials, String workContractAddress, BigInteger workId,
			String committeeContractAddress, BigInteger committeeId) {
		Committee committeeContract = contractUtil.getCommittee(credentials);
		TransactionReceipt tempTransactionReceipt = null;
		try {
			tempTransactionReceipt = committeeContract.proposal(credentials.getAddress(), "").send();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
		}
		TransactionReceipt transactionReceipt = tempTransactionReceipt;
		logger.info(JSON.toJSONString(transactionReceipt));
		committeeContract
				.afterProposalEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
				.subscribe(event -> {
					if (event.log.getTransactionHash().equals(transactionReceipt.getTransactionHash())) {
						logger.info("ProposlId:" + event._id.toString());
						BigInteger id = event._id;
						committeeContract.setFromWorkContract(id, workContractAddress).send();
						committeeContract.setFromWork(id, workId).send();
						committeeContract.proposalVoteProgressEventFlowable(DefaultBlockParameterName.EARLIEST,
								DefaultBlockParameterName.LATEST).subscribe(event1 -> {
									if (event1._proposalId.equals(id) && event1._totalmember.equals(event1._finished)) {
										Boolean finalResult = committeeContract
												.getProposalFinalResult(credentials.getAddress(), id).send();
										logger.info("finalResult:" + finalResult);
										commodifyRight(credentials, id, workContractAddress, workId,
												committeeContractAddress);

									}
								});
						int committeeMemberNum = committeeContract.getCommitteeMemberNum(committeeId).send().intValue();
						for (int i = 0; i < committeeMemberNum; i++) {
							String memberAddress = committeeContract
									.getCommitteeMember(committeeId, BigInteger.valueOf(i)).send();
							User member = userService.selectByBlockChainAddress(memberAddress);
							Credentials memberCredentials = contractUtil.getCredentials(member.getPrivateKey());
							Committee tempCommitteeContract = contractUtil.getCommittee(memberCredentials);
							tempCommitteeContract.voteProposal(committeeId, id, true).send();
						}
					}
				});

	}

	public void commodifyRight(Credentials credentials, BigInteger proposalId, String workContractAddress,
			BigInteger workId, String committeeContractAddress) {
		CommodifyRight commodifyRightContract = contractUtil.getCommodifyRight(credentials);
		try {
			commodifyRightContract.release(proposalId, workContractAddress, workId, committeeContractAddress).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
		}

	}

	public void buyWorkCommodity(Credentials credentials, BigInteger workId, BuyCommodityEvent buyCommodityEvent) {
		BigInteger projectId = workProjectMapper.selectProjectId(workId);
		Work workContract = contractUtil.getWork(credentials);
		try {
			String workOwnerBlockChainAddress = workContract.getPresentOwner(workId).send();
			User workOwner = userService.selectByBlockChainAddress(workOwnerBlockChainAddress.toLowerCase());
			Credentials workOwnerCredentials = contractUtil.getCredentials(workOwner.getPrivateKey());
			buyCommodity(workOwnerCredentials, ethConfig.getProjectAddress(), projectId,
					ethConfig.getCommodifyRightAddress(), credentials.getAddress(), buyCommodityEvent);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void transfer(Credentials credentials, String to, BigInteger commodityId) {
		Commodity commodityContract = contractUtil.getCommodity(credentials);
		try {
			commodityContract.transfer(contractUtil.getCredentials(to).getAddress(), commodityId).send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 
	 * @param workOwnerCredentials          作品拥有者登入凭证
	 * @param projectContractAddress
	 * @param projectId
	 * @param commodifyRightContractAddress
	 * @param toBlockChainAddress
	 * @param buyCommodityEvent
	 */
	private void buyCommodity(Credentials workOwnerCredentials, String projectContractAddress, BigInteger projectId,
			String commodifyRightContractAddress, String toBlockChainAddress, BuyCommodityEvent buyCommodityEvent) {
		Commodity commodityContract = contractUtil.getCommodity(workOwnerCredentials);
		TransactionReceipt tempTransactionReceipt = null;
		try {
			tempTransactionReceipt = commodityContract
					.release(projectContractAddress, projectId, commodifyRightContractAddress).send();
		} catch (Exception e) {
			e.printStackTrace();
			throw new BaseException(ExceptionEnum.CONTRACT_FUNC_FAILED);
		}
		TransactionReceipt transactionReceipt = tempTransactionReceipt;
		commodityContract
				.afterReleaseEventFlowable(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST)
				.subscribe(event -> {
					if (event.log.getTransactionHash().equals(transactionReceipt.getTransactionHash())) {
						BigInteger commodityId = event._commodityId;
						logger.info("CommodityId:" + commodityId);
						commodityContract.transfer(toBlockChainAddress.toLowerCase(), commodityId).send();
						buyCommodityEvent.getTransaction().setFlag(true);
						buyCommodityEvent.setResponse(event);
						eventPublisher.publishEvent(buyCommodityEvent);
					}
				});
	}

	/**
	 * 查找用户拥有的iptoken
	 * 
	 * @param credentials 该用户的登入凭证
	 * @return
	 */
	public List<IPToken> selectByOwner(Credentials credentials) {
		List<IPToken> tempIpTokens = new ArrayList<IPToken>();
		Work workContract = contractUtil.getWork(credentials);
		int supply;
		try {
			supply = workContract.getSupply().send().intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.INFO_SEARCH_FAILED);
		}
		// 遍历iptoken
		for (int i = 1; i <= supply; i++) {
			try {
				IPToken ipToken = selectById(credentials, BigInteger.valueOf(i));
				// 判断该iptoken是否是该用户
				String presentOwner = ipToken.getCreator().getBlockChainAddress();
				if (presentOwner.equals(credentials.getAddress())) {
					tempIpTokens.add(ipToken);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		logger.info(JSON.toJSONString(tempIpTokens));
		return tempIpTokens;
	}

	/**
	 * 查找所有的iptoken
	 * 
	 * @param credentials
	 * @return
	 */
	public List<IPToken> selectAll(Credentials credentials) {
		List<IPToken> tempIpTokens = new ArrayList<IPToken>();
		Work workContract = contractUtil.getWork(credentials);
		int supply;
		try {
			supply = workContract.getSupply().send().intValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.INFO_SEARCH_FAILED);
		}

		// 遍历
		for (int i = 1; i <= supply; i++) {
			try {
				IPToken ipToken = selectById(credentials, BigInteger.valueOf(i));
				tempIpTokens.add(ipToken);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		logger.info(JSON.toJSONString(tempIpTokens));
		return tempIpTokens;
	}

	/**
	 * 通过iptoken的id来查找
	 * 
	 * @param credentials 用户凭据，用户不一定是该ip的拥有者
	 * @param id          iptoken的id
	 * @return 如果未找到报错BaseException(ExceptionEnum.INFO_SEARCH_FAILED)
	 */
	public IPToken selectById(Credentials credentials, BigInteger id) {
		Work workContract = contractUtil.getWork(credentials);
		try {
			String presentOwnerAddress = workContract.getPresentOwner(id).send();
			String fileHash = workContract.getIpfsHashByWorkTokenId(id).send();
			IPToken iptoken = new IPToken();
			iptoken.setFileHash(fileHash);
			User user = userService.selectByBlockChainAddress(presentOwnerAddress);
			logger.info(user.toString());
			iptoken.setCreator(user);
			iptoken.setContractAddress(ethConfig.getAbstractAddress());
			iptoken.setTokenID(id);
			return iptoken;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BaseException(ExceptionEnum.INFO_SEARCH_FAILED);
		}

	}
}
