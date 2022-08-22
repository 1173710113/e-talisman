package com.example.demo.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.config.IPFSConfig;
import com.example.demo.msghandler.BaseException;
import com.example.demo.msghandler.ExceptionEnum;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;

@Component
public class FileUtil {

	@Autowired
	private IPFSConfig ipfsConfig;

	private IPFS ipfs;

	@PostConstruct
	public void postConstruct() {
		try {
			ipfs = new IPFS(ipfsConfig.getUrl());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public MerkleNode upload(MultipartFile file) {
		try {
			NamedStreamable namedStreamable = new NamedStreamable.ByteArrayWrapper(file.getBytes());
			MerkleNode node = ipfs.add(namedStreamable).get(0);
			return node;
		} catch (Exception e) {
			// 异常处理
			throw new BaseException(ExceptionEnum.FILE_SAVE_FAILED);
		}
	}

	public MerkleNode upload(String name, MultipartFile file) {
		try {
			NamedStreamable namedStreamable = new NamedStreamable.ByteArrayWrapper(name, file.getBytes());
			MerkleNode node = ipfs.add(namedStreamable).get(0);
			return node;
		} catch (Exception e) {
			// 异常处理
			throw new BaseException(ExceptionEnum.FILE_SAVE_FAILED);
		}
	}

	public MerkleNode upload(String name, byte[] bytes) {
		try {
			NamedStreamable namedStreamable = new NamedStreamable.ByteArrayWrapper(name, bytes);
			MerkleNode node = ipfs.add(namedStreamable).get(0);
			return node;
		} catch (IOException e) {
			// 异常处理
			throw new BaseException(ExceptionEnum.FILE_SAVE_FAILED);
		}
	}
	
	public MerkleNode upload(byte[] bytes) {
		try {
			NamedStreamable namedStreamable = new NamedStreamable.ByteArrayWrapper(bytes);
			MerkleNode node = ipfs.add(namedStreamable).get(0);
			return node;
		} catch (IOException e) {
			// 异常处理
			throw new BaseException(ExceptionEnum.FILE_SAVE_FAILED);
		}
	}
	
	public Map<String, MerkleNode> upload(Map<String, MultipartFile> files) {
		Map<String, MerkleNode> map = new HashMap<String, MerkleNode>();
		for(Map.Entry<String, MultipartFile> entry : files.entrySet()) {
			map.put(entry.getKey(), upload(entry.getValue()));
		}
		return map;
		
	}
	
	public List<MerkleNode> upload(List<MultipartFile> files){
		List<MerkleNode> nodes = new ArrayList<MerkleNode>();
		for(MultipartFile file : files) {
			nodes.add(upload(file));
		}
		return nodes;
	}
	
	public byte[] get(String hash) {
		try {
			Multihash multihash = Multihash.fromBase58(hash);
			byte[] bytes = ipfs.cat(multihash);
			return bytes;
		} catch (IOException e) {
			throw new BaseException(ExceptionEnum.FILE_SAVE_FAILED);
		}
	}
}
