package com.example.demo.domain;

import java.math.BigInteger;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "微信购买云祝福订单")
public class Order {

	@ApiModelProperty(value = "订单ID")
	protected String id;// 订单ID

	@ApiModelProperty(value = "创建时间")
	protected Date createdTime;// 创建时间

	@ApiModelProperty(value = "结束时间")
	protected Date finishTime;// 订单结束时间

	@ApiModelProperty(value = "购买的符的ID")
	protected BigInteger talismanId;

	@ApiModelProperty(value = "购买的模板的ID")
	protected BigInteger artId;

	protected BigInteger talismanAmount;

	protected BigInteger artAmount;

	protected Integer talismanPriceCent;

	protected Integer artPriceCent;

	protected String paymentPlan;

	@ApiModelProperty(value = "描述")
	protected String description;

	@ApiModelProperty(value = "是否成功")
	protected Boolean flag;

	@ApiModelProperty(value = "是否结束")
	protected Boolean isDone;

	@ApiModelProperty(value = "总金额（分）")
	protected Integer total;

	@ApiModelProperty(value = "购买者")
	protected User buyer;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	public BigInteger getTalismanId() {
		return talismanId;
	}

	public void setTalismanId(BigInteger talismanId) {
		this.talismanId = talismanId;
	}

	public BigInteger getArtId() {
		return artId;
	}

	public void setArtId(BigInteger artId) {
		this.artId = artId;
	}

	public BigInteger getTalismanAmount() {
		return talismanAmount;
	}

	public void setTalismanAmount(BigInteger talismanAmount) {
		this.talismanAmount = talismanAmount;
	}

	public BigInteger getArtAmount() {
		return artAmount;
	}

	public void setArtAmount(BigInteger artAmount) {
		this.artAmount = artAmount;
	}

	public Integer getTalismanPriceCent() {
		return talismanPriceCent;
	}

	public void setTalismanPriceCent(Integer talismanPriceCent) {
		this.talismanPriceCent = talismanPriceCent;
	}

	public Integer getArtPriceCent() {
		return artPriceCent;
	}

	public void setArtPriceCent(Integer artPriceCent) {
		this.artPriceCent = artPriceCent;
	}

	public String getPaymentPlan() {
		return paymentPlan;
	}

	public void setPaymentPlan(String paymentPlan) {
		this.paymentPlan = paymentPlan;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public User getBuyer() {
		return buyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createdTime=" + createdTime + ", finishTime=" + finishTime + ", talismanId="
				+ talismanId + ", artId=" + artId + ", talismanAmount=" + talismanAmount + ", artAmount=" + artAmount
				+ ", talismanPriceCent=" + talismanPriceCent + ", artPriceCent=" + artPriceCent + ", paymentPlan="
				+ paymentPlan + ", description=" + description + ", flag=" + flag + ", isDone=" + isDone + ", total="
				+ total + ", buyer=" + buyer + "]";
	}

}
