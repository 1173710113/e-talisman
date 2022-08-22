package com.example.demo.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.AccessPaymentPlan;
import com.example.demo.domain.ArtIPToken;

import io.ipfs.api.MerkleNode;

public interface AntChainArtService {

	public MerkleNode saveArtInfo(List<MultipartFile> files, AccessPaymentPlan accessPaymentPlan, Integer totalSupply);

	public List<ArtIPToken> selectIArtByOwner(String openId);

	public List<ArtIPToken> selectAllArt();

	public ArtIPToken selectArtById(BigInteger id);

}
