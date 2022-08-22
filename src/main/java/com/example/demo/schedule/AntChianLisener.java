package com.example.demo.schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.antfinancial.mychain.baas.tool.restclient.response.BaseResp;
import com.example.demo.config.AntChainConfig;
import com.example.demo.contract.service.AntBlockChainService;
import com.example.demo.domain.ArtIPToken;
import com.example.demo.domain.ETalisman;
import com.example.demo.domain.IPToken;
import com.example.demo.domain.TalismanIPToken;
import com.example.demo.domain.Transaction;
import com.example.demo.domain.TransactionType;
import com.example.demo.domain.User;
import com.example.demo.domain.factory.IPFactory;
import com.example.demo.mapper.ArtMapper;
import com.example.demo.mapper.ETalismanMapper;
import com.example.demo.mapper.TalismanMapper;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;
import com.example.demo.service.UserService;

@Component
public class AntChianLisener {

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private AntBlockChainService antBlockChainService;

	@Autowired
	private UserService userService;

	@Autowired
	private AntChainConfig antChainConfig;

	@Autowired
	private TalismanMapper talismanMapper;

	@Autowired
	private ArtMapper artMapper;
	
	@Autowired
	private ETalismanMapper etalismanMapper;
	

	private static Logger logger = LoggerFactory.getLogger(AntChianLisener.class);

	
	@Transactional
	@Scheduled(fixedRate = 60000)
	public void queryNewTalisman() throws Exception {
		logger.info("QUERY NEW TALISMAN START");
		Transaction condition = new Transaction();
		condition.setType(TransactionType.NEWTALISMAN);
		condition.setDone(false);
		List<Transaction> transactions = transactionMapper.select(condition);
		for (Transaction transaction : transactions) {
			BaseResp resp = antBlockChainService.queryOutput(transaction.getTxHash(),
					"[\"uint256\",\"uint256\",\"uint256\"]");
			logger.info(JSON.toJSONString(resp));
			JSONArray jsonArray = JSON.parseArray(resp.getData());
			String hash = transaction.getData();
			IPToken ipToken = new IPToken();
			ipToken.setFileHash(hash);
			ipToken.setType("TALISMAN");
			ipToken.setTokenID(jsonArray.getBigInteger(2));
			User creator = userService.selectByOpenId(transaction.getFrom());
			ipToken.setCreator(creator);
			ipToken.setContractAddress(antBlockChainService.address(antChainConfig.getProjectContractName()));
			TalismanIPToken talismanIPToken = IPFactory.getTalismanIPToken(ipToken);
			talismanIPToken.setAbstractId(jsonArray.getBigInteger(0));
			talismanIPToken.setProjectId(jsonArray.getBigInteger(1));
			talismanMapper.insert(talismanIPToken);
			transactionSuccess(transaction);
		}
	}

	@Transactional
	@Scheduled(fixedRate = 60000)
	public void queryNewArt() throws Exception {
		logger.info("QUERY NEW ART START");
		Transaction condition = new Transaction();
		condition.setType(TransactionType.NEWART);
		condition.setDone(false);
		List<Transaction> transactions = transactionMapper.select(condition);
		for (Transaction transaction : transactions) {
			BaseResp resp = antBlockChainService.queryOutput(transaction.getTxHash(),
					"[\"uint256\",\"uint256\",\"uint256\"]");
			logger.info(JSON.toJSONString(resp));
			JSONArray jsonArray = JSON.parseArray(resp.getData());
			String hash = transaction.getData();
			IPToken ipToken = new IPToken();
			ipToken.setFileHash(hash);
			ipToken.setType("TALISMAN");
			ipToken.setTokenID(jsonArray.getBigInteger(2));
			User creator = userService.selectByOpenId(transaction.getFrom());
			ipToken.setCreator(creator);
			ipToken.setContractAddress(antBlockChainService.address(antChainConfig.getProjectContractName()));
			ArtIPToken artIPToken = IPFactory.getArtIPToken(ipToken);
			artIPToken.setAbstractId(jsonArray.getBigInteger(0));
			artIPToken.setProjectId(jsonArray.getBigInteger(1));
			artMapper.insert(artIPToken);
			transactionSuccess(transaction);
		}
	}

	@Transactional
	@Scheduled(fixedRate = 60000)
	public void queryNewETalisman() throws Exception {
		logger.info("QUERY NEW ETALISMAN START");
		Transaction condition = new Transaction();
		condition.setType(TransactionType.NEWETALISMAN);
		condition.setDone(false);
		List<Transaction> transactions = transactionMapper.select(condition);
		for (Transaction transaction : transactions) {
			BaseResp resp = antBlockChainService.queryOutput(transaction.getTxHash(), "[\"uint256\",\"uint256\"]");
			logger.info(JSON.toJSONString(resp));
			JSONArray jsonArray = JSON.parseArray(resp.getData());
			JSONObject object = JSON.parseObject(transaction.getData());
			ETalisman eTalisman = new ETalisman();
			User owner = new User();
			owner.setOpenId(transaction.getFrom());
			eTalisman.setOwner(owner);
			eTalisman.setTalismanId(object.getBigInteger("talismanId"));
			eTalisman.setArtId(object.getBigInteger("artId"));
			eTalisman.setTalismanCommodityId(jsonArray.getBigInteger(0));
			eTalisman.setArtCommodityId(jsonArray.getBigInteger(1));
			String paymentPlan = object.getString("paymentPlan");
			eTalisman.setExpireTime(getExpiredTime(paymentPlan));
			logger.info(JSON.toJSONString(eTalisman));
			etalismanMapper.insert(eTalisman);
			transactionSuccess(transaction);
		}
	}
	
	@Transactional
	@Scheduled(fixedRate = 60000)
	public void queryTransferETalisman() throws Exception {
		logger.info("QUERY TRANSFER ETALISMAN START");
		Transaction condition = new Transaction();
		condition.setType(TransactionType.TRANSFERTALISMANCOMMODITY);
		condition.setDone(false);
		List<Transaction> transferTalismanCommodityTransactions = transactionMapper.select(condition);
		for(Transaction transferTalismanCommdoityTransaction : transferTalismanCommodityTransactions) {
			BaseResp resp = antBlockChainService.queryOutput(transferTalismanCommdoityTransaction.getTxHash(), "[]");
			logger.info(JSON.toJSONString(resp));
			String data = transferTalismanCommdoityTransaction.getData();
			JSONObject dataObject = JSON.parseObject(data);
			condition.setType(TransactionType.TRANSFERARTCOMMODITY);
			condition.setData(data);
			List<Transaction> transferArtCommodityTransactions = transactionMapper.select(condition);
			if(transferArtCommodityTransactions.size() != 1) {
				logger.error("TransferETalismanError:" + JSON.toJSONString(transferArtCommodityTransactions));
			} else {
				Transaction transferArtCommodityTransaction = transferArtCommodityTransactions.get(0);
				BaseResp resp_ = antBlockChainService.queryOutput(transferArtCommodityTransaction.getTxHash(), "[]");
				logger.info(JSON.toJSONString(resp_));
				transactionSuccess(transferArtCommodityTransaction);
				transactionSuccess(transferTalismanCommdoityTransaction);
				ETalisman etalisman = etalismanMapper.selectById(dataObject.getBigInteger("etalismanId"));
				User owner = new User();
				owner.setOpenId(dataObject.getString("toOpenId"));
				etalisman.setOwner(owner);
				etalismanMapper.updateById(etalisman);
			}
		}
		
	}
	
	private void transactionSuccess(Transaction transaction) {
		transaction.setDone(true);
		transaction.setFlag(true);
		transaction.setFinishTime(new Date());
		transactionMapper.updateById(transaction);
	}

	private Date getExpiredTime(String paymentPlan) {
		Calendar rightNow = Calendar.getInstance();
		Date date = new Date();
		rightNow.setTime(date);
		switch (paymentPlan) {
		case "monthlyPayment":
			rightNow.add(Calendar.MONTH, 1);
			return rightNow.getTime();
		case "yearlyPayment":
			rightNow.add(Calendar.YEAR, 1);
			return rightNow.getTime();
		case "fullyPayment":
			return null;
		default:
			throw new BaseException(ExceptionEnum.AUGUMENT_ERROR);
		}
	}
}
