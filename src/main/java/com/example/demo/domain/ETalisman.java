package com.example.demo.domain;

import java.math.BigInteger;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "云祝福")
public class ETalisman {

	@ApiModelProperty(value = "云祝福ID")
	private BigInteger etalismanId;

	@ApiModelProperty(value = "符ID")
	private BigInteger talismanId;

	@ApiModelProperty(value = "符商品ID")
	private BigInteger talismanCommodityId;

	@ApiModelProperty(value = "模板ID")
	private BigInteger artId;

	@ApiModelProperty(value = "模板商品ID")
	private BigInteger artCommodityId;

	@ApiModelProperty(value = "拥有者openId")
	private User owner;

	@ApiModelProperty(value = "符")
	private TalismanIPToken talisman;

	@ApiModelProperty(value = "模板")
	private ArtIPToken art;

	@ApiModelProperty(value = "到期时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date expireTime;

	public BigInteger getEtalismanId() {
		return etalismanId;
	}

	public void setEtalismanId(BigInteger etalismanId) {
		this.etalismanId = etalismanId;
	}

	public BigInteger getTalismanId() {
		return talismanId;
	}

	public void setTalismanId(BigInteger talismanId) {
		this.talismanId = talismanId;
	}

	public BigInteger getTalismanCommodityId() {
		return talismanCommodityId;
	}

	public void setTalismanCommodityId(BigInteger talismanCommodityId) {
		this.talismanCommodityId = talismanCommodityId;
	}

	public BigInteger getArtId() {
		return artId;
	}

	public void setArtId(BigInteger artId) {
		this.artId = artId;
	}

	public BigInteger getArtCommodityId() {
		return artCommodityId;
	}

	public void setArtCommodityId(BigInteger artCommodityId) {
		this.artCommodityId = artCommodityId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public TalismanIPToken getTalisman() {
		return talisman;
	}

	public void setTalisman(TalismanIPToken talisman) {
		this.talisman = talisman;
	}

	public ArtIPToken getArt() {
		return art;
	}

	public void setArt(ArtIPToken art) {
		this.art = art;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

}
